package com.techullurgy.dosthman.presentation.components.root

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filterIsInstance

@Suppress("AssignedValueIsNeverRead")
@Composable
internal fun AppEditableText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textStyle: TextStyle = LocalTextStyle.current
) {
    var isInEditMode by retain { mutableStateOf(false) }

    if (!isInEditMode) {
        Text(
            text = text,
            maxLines = if (singleLine) 1 else Int.MAX_VALUE,
            overflow = overflow,
            style = textStyle,
            modifier = modifier
                .combinedClickable(
                    onClick = {},
                    onDoubleClick = { isInEditMode = true }
                )
        )
    } else {
        val textState = retain { TextFieldState(text) }

        val focusRequester = retain { FocusRequester() }
        val interactionSource = retain { MutableInteractionSource() }

        LaunchedEffect(Unit) { focusRequester.requestFocus() }

        LaunchedEffect(Unit) {
            snapshotFlow { textState.text.toString() }.collectLatest(onTextChange)
        }

        LaunchedEffect(Unit) {
            interactionSource
                .interactions
                .filterIsInstance<FocusInteraction>()
                .dropWhile {
                    it !is FocusInteraction.Focus
                }
                .filterIsInstance<FocusInteraction.Unfocus>()
                .collect { isInEditMode = false }
        }

        BasicTextField(
            state = textState,
            interactionSource = interactionSource,
            textStyle = TextStyle(color = LocalContentColor.current).merge(textStyle),
            lineLimits = if (singleLine) TextFieldLineLimits.SingleLine else TextFieldLineLimits.Default,
            modifier = modifier
                .focusRequester(focusRequester)
        )
    }
}