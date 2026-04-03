package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.longOrNull

@Composable
fun JsonViewer(
    json: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    colors: JsonViewerColors = JsonViewerColors()
) {
    val element = remember(json) { Json.parseToJsonElement(json) }
    val state = remember { JsonViewerState() }

    Column(
        modifier
            .padding(8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        ProvideTextStyle(
            LocalTextStyle.current.copy(
                fontSize = fontSize
            )
        ) {
            CompositionLocalProvider(
                LocalJsonViewerState provides state,
                LocalJsonViewerColors provides colors
            ) {
                JsonNode(
                    element = element,
                    path = "root",
                )
            }
        }
    }
}

data class JsonViewerColors(
    val keyTextColor: Color = Color.Blue,
    val valueTextColor: Color = Color.Cyan,
    val quoteColor: Color = Color.Green,
    val numberColor: Color = Color.Blue,
    val booleanColor: Color = Color.Magenta,
    val placeholderColer: Color = Color.LightGray,
    val collapseToggleColor: Color = Color.DarkGray,
    val bracketColor: Color = Color.Green
)

@Composable
private fun JsonNode(
    element: JsonElement,
    path: String,
) {
    when (element) {
        is JsonObject -> JsonObjectNode(element, path)
        is JsonArray -> JsonArrayNode(element, path)
        is JsonPrimitive -> JsonPrimitiveNode(element)
    }
}

@Composable
private fun JsonObjectNode(
    obj: JsonObject,
    path: String,
) {
    val jsonViewerState = LocalJsonViewerState.current

    val expanded = jsonViewerState.isExpanded(path)

    Row {
        Text(
            text = if (expanded) "▼" else "▶",
            color = LocalJsonViewerColors.current.collapseToggleColor,
            modifier = Modifier.clickable { jsonViewerState.toggle(path) }
        )

        Text(
            text = "{ } (${obj.size})",
            color = LocalJsonViewerColors.current.placeholderColer
        )
    }

    if (expanded) {
        Column {
            Text("{", color = LocalJsonViewerColors.current.bracketColor)
            obj.entries.forEach { (key, value) ->
                val childPath = "$path.$key"

                Row(
                    modifier = Modifier.padding(start = (INDENT_PADDING).dp)
                ) {
                    val annotatedKey = buildAnnotatedString {
                        val quoteStyle = SpanStyle(color = LocalJsonViewerColors.current.quoteColor)
                        val keyTextStyle = SpanStyle(color = LocalJsonViewerColors.current.keyTextColor)

                        withStyle(quoteStyle) { append("\"") }
                        withStyle(keyTextStyle) { append(key) }
                        withStyle(quoteStyle) { append("\"") }
                    }

                    Text(annotatedKey)

                    JsonNode(
                        element = value,
                        path = childPath,
                    )
                }
            }
            Text("}", color = LocalJsonViewerColors.current.bracketColor)
        }
    }
}


@Composable
private fun JsonArrayNode(
    array: JsonArray,
    path: String,
) {
    val jsonViewerState = LocalJsonViewerState.current

    val expanded = jsonViewerState.isExpanded(path)

    Row {
        Text(
            text = if (expanded) "▼" else "▶",
            color = LocalJsonViewerColors.current.collapseToggleColor,
            modifier = Modifier.clickable { jsonViewerState.toggle(path) }
        )

        Text(
            text = "[ ] (${array.size})",
            color = LocalJsonViewerColors.current.placeholderColer
        )
    }

    if (expanded) {
        Column {
            Text("[", color = LocalJsonViewerColors.current.bracketColor)
            array.forEachIndexed { index, item ->
                val childPath = "$path[$index]"

                Column(
                    modifier = Modifier.padding(start = INDENT_PADDING.dp)
                ) {
                    JsonNode(
                        element = item,
                        path = childPath,
                    )
                }
            }
            Text("]", color = LocalJsonViewerColors.current.bracketColor)
        }
    }
}


@Composable
private fun JsonPrimitiveNode(
    primitive: JsonPrimitive,
) {
    val value = when {
        primitive.isString -> buildAnnotatedString {
            val quoteStyle = SpanStyle(color = LocalJsonViewerColors.current.quoteColor)
            val valueTextStyle = SpanStyle(color = LocalJsonViewerColors.current.valueTextColor)

            withStyle(quoteStyle) { append("\"") }
            withStyle(valueTextStyle) { append(primitive.content) }
            withStyle(quoteStyle) { append("\"") }
        }
        primitive.booleanOrNull != null -> AnnotatedString(primitive.boolean.toString())
        primitive.intOrNull != null -> AnnotatedString(primitive.int.toString())
        primitive.doubleOrNull != null -> AnnotatedString(primitive.double.toString())
        else -> AnnotatedString(primitive.content)
    }

    val color = when {
        primitive.booleanOrNull != null -> LocalJsonViewerColors.current.booleanColor
        primitive.intOrNull != null || primitive.doubleOrNull != null || primitive.longOrNull != null -> LocalJsonViewerColors.current.numberColor
        else -> LocalJsonViewerColors.current.valueTextColor
    }

    Text(
        text = value,
        color = color,
        modifier = Modifier.padding(start = INDENT_PADDING.dp)
    )
}

private typealias JsonPath = String

private class JsonViewerState {
    private val expandedMap = mutableStateMapOf<JsonPath, Boolean>()

    fun isExpanded(path: JsonPath): Boolean {
        return expandedMap[path] ?: false
    }

    fun toggle(path: JsonPath) {
        expandedMap[path] = !(expandedMap[path] ?: false)
    }
}

private val LocalJsonViewerState = staticCompositionLocalOf<JsonViewerState> { error("No value provided for LocalJsonViewerState") }
private val LocalJsonViewerColors = staticCompositionLocalOf<JsonViewerColors> { error("No value provided for LocalJsonViewerState") }

@Composable
@Preview
private fun JsonViewerPreview() {
    val json = """
        {
            "name": "Prakash",
            "age": 29,
            "gender": "male",
            "address": {
                "street": "Main Street",
                "place": "Chennai",
                "contacts": {
                    "mobile": "9898898989",
                    "alternate_mobile": "8392898374",
                    "emails": ["test1@test.com", "test2@test.com"]
                }
            },
            "resources": [
                {
                    "group": "Group 1",
                    "is_available": false
                },
                {
                    "group": "Group 2",
                    "is_available": true
                },
                {
                    "group": "Group 3",
                    "is_available": false
                }
            ]
        }
    """.trimIndent()

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        JsonViewer(json = json)
    }
}

private const val INDENT_PADDING = 16