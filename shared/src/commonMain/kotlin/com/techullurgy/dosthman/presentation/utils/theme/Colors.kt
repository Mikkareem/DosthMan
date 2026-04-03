package com.techullurgy.dosthman.presentation.utils.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

enum class ColorToken(val color: Color, val contentColor: Color) {
    Green50(Color(0xFFEAFBF2), Color.Black),
    Green100(Color(0xFFC3F4DA), Color.Black),
    Green200(Color(0xFF9CECC3), Color.Black),
    Green300(Color(0xFF76E5AC), Color.Black),
    Green400(Color(0xFF4FDE94), Color.Black),
    Green500(Color(0xFF29D67D), Color.Black),
    Green600(Color(0xFF21B066), Color.White),
    Green700(Color(0xFF1A8950), Color.White),
    Green800(Color(0xFF136339), Color.White),
    Green900(Color(0xFF0B3C23), Color.White),
    Green950(Color(0xFF04150C), Color.White),

    Orange50(Color(0xFFFEF1E7), Color.Black),
    Orange100(Color(0xFFFCD8BB), Color.Black),
    Orange200(Color(0xFFFABF8F), Color.Black),
    Orange300(Color(0xFFF8A663), Color.Black),
    Orange400(Color(0xFFF68D37), Color.Black),
    Orange500(Color(0xFFF2730C), Color.Black),
    Orange600(Color(0xFFC85F09), Color.White),
    Orange700(Color(0xFF9C4A07), Color.White),
    Orange800(Color(0xFF703505), Color.White),
    Orange900(Color(0xFF442003), Color.White),
    Orange950(Color(0xFF180C01), Color.White),

    Yellow50(Color(0xFFFEFDE7), Color.Black),
    Yellow100(Color(0xFFFCFBBB), Color.Black),
    Yellow200(Color(0xFFFAF88F), Color.Black),
    Yellow300(Color(0xFFF8F563), Color.Black),
    Yellow400(Color(0xFFF6F237), Color.Black),
    Yellow500(Color(0xFFF2EE0C), Color.Black),
    Yellow600(Color(0xFFC8C509), Color.White),
    Yellow700(Color(0xFF9C9907), Color.White),
    Yellow800(Color(0xFF706E05), Color.White),
    Yellow900(Color(0xFF444303), Color.White),
    Yellow950(Color(0xFF181801), Color.White),

    Teal50(Color(0xFFE8FDF8), Color.Black),
    Teal100(Color(0xFFBEF9EB), Color.Black),
    Teal200(Color(0xFF94F4DE), Color.Black),
    Teal300(Color(0xFF6AF0D1), Color.Black),
    Teal400(Color(0xFF41ECC4), Color.Black),
    Teal500(Color(0xFF17E8B7), Color.Black),
    Teal600(Color(0xFF13BE96), Color.White),
    Teal700(Color(0xFF0F9575), Color.White),
    Teal800(Color(0xFF0B6B54), Color.White),
    Teal900(Color(0xFF064133), Color.White),
    Teal950(Color(0xFF021712), Color.White),

    Pink50(Color(0xFFFCE8FD), Color.Black),
    Pink100(Color(0xFFF7BEF9), Color.Black),
    Pink200(Color(0xFFF194F4), Color.Black),
    Pink300(Color(0xFFEC6AF0), Color.Black),
    Pink400(Color(0xFFE641EC), Color.Black),
    Pink500(Color(0xFFE117E8), Color.Black),
    Pink600(Color(0xFFB913BE), Color.White),
    Pink700(Color(0xFF900F95), Color.White),
    Pink800(Color(0xFF680B6B), Color.White),
    Pink900(Color(0xFF3F0641), Color.White),
    Pink950(Color(0xFF170217), Color.White),

    Red50(Color(0xFFFDE8EC), Color.Black),
    Red100(Color(0xFFF9BECB), Color.Black),
    Red200(Color(0xFFF494A9), Color.Black),
    Red300(Color(0xFFF06A87), Color.Black),
    Red400(Color(0xFFEC4166), Color.Black),
    Red500(Color(0xFFE81744), Color.Black),
    Red600(Color(0xFFBE1338), Color.White),
    Red700(Color(0xFF950F2C), Color.White),
    Red800(Color(0xFF6B0B1F), Color.White),
    Red900(Color(0xFF410613), Color.White),
    Red950(Color(0xFF170207), Color.White),

    Blue50(Color(0xFFEAE7FD), Color.Black),
    Blue100(Color(0xFFC3BCFA), Color.Black),
    Blue200(Color(0xFF9D91F7), Color.Black),
    Blue300(Color(0xFF7766F4), Color.Black),
    Blue400(Color(0xFF513BF1), Color.Black),
    Blue500(Color(0xFF2911ED), Color.Black),
    Blue600(Color(0xFF230EC4), Color.White),
    Blue700(Color(0xFF1B0B99), Color.White),
    Blue800(Color(0xFF14086E), Color.White),
    Blue900(Color(0xFF0C0543), Color.White),
    Blue950(Color(0xFF040218), Color.White),

    Purple50(Color(0xFFF4E8FD), Color.Black),
    Purple100(Color(0xFFE1BEF9), Color.Black),
    Purple200(Color(0xFFCE94F4), Color.Black),
    Purple300(Color(0xFFBB6AF0), Color.Black),
    Purple400(Color(0xFFA841EC), Color.Black),
    Purple500(Color(0xFF961CE8), Color.Black),
    Purple600(Color(0xFF7A13BE), Color.White),
    Purple700(Color(0xFF5F0F95), Color.White),
    Purple800(Color(0xFF440B6B), Color.White),
    Purple900(Color(0xFF2A0641), Color.White),
    Purple950(Color(0xFF0F0217), Color.White),

    NeutralBlack50(Color(0xFF383331), Color.White),
    NeutralBlack100(Color(0xFF3F3A38), Color.White),
    NeutralBlack200(Color(0xFF464140), Color.White),
    NeutralBlack300(Color(0xFF4D4947), Color.White),
    NeutralBlack400(Color(0xFF54504F), Color.White),
    NeutralBlack500(Color(0xFF5B5857), Color.White),
    NeutralBlack600(Color(0xFF62605F), Color.White),
    NeutralBlack700(Color(0xFF696867), Color.White),
    NeutralBlack800(Color(0xFF71706F), Color.White),
    NeutralBlack900(Color(0xFF787878), Color.White),
    NeutralBlack950(Color(0xFF808080), Color.White),

    NeutralWhite50(Color(0xFFFFFEFE), Color.Black),
    NeutralWhite100(Color(0xFFFEFDFC), Color.Black),
    NeutralWhite200(Color(0xFFFEFCFB), Color.Black),
    NeutralWhite300(Color(0xFFFEFBF9), Color.Black),
    NeutralWhite400(Color(0xFFFDFAF8), Color.Black),
    NeutralWhite500(Color(0xFFFDF9F6), Color.Black),
    NeutralWhite600(Color(0xFFFCF8F5), Color.Black),
    NeutralWhite700(Color(0xFFFCF7F3), Color.Black),
    NeutralWhite800(Color(0xFFFCF6F2), Color.Black),
    NeutralWhite900(Color(0xFFFBF5F0), Color.Black),
    NeutralWhite950(Color(0xFFFBF4EF), Color.Black),

    NeutralTransparent(Color(0x00000000), Color.Black)
}

@Composable
private fun ColorBox(
    color: Color,
    text: String? = "",
    textColor: Color? = null,
) {
    Box(
        modifier = Modifier.background(color).padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        text?.let {
            Text(
                text = it,
                color = textColor ?: LocalContentColor.current
            )
        }
    }
}

@Composable
@Preview
private fun ColorBoxPreview(
    @PreviewParameter(ColorBoxPreviewDataProvider::class) data: ColorBoxPreviewData
) {
    ColorBox(
        color = data.color,
        text = data.text,
        textColor = data.textColor
    )
}

private data class ColorBoxPreviewData(
    val color: Color,
    val text: String?,
    val textColor: Color?
)

private class ColorBoxPreviewDataProvider: PreviewParameterProvider<ColorBoxPreviewData> {
    override val values: Sequence<ColorBoxPreviewData> = ColorToken.entries.map {
        ColorBoxPreviewData(
            color = it.color,
            text = it.name,
            textColor = it.contentColor
        )
    }.asSequence()
}