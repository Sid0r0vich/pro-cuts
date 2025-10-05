package com.sidor.procuts.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun BlurText(
    text: AnnotatedString,
    loadingIsCompleted: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle? = null,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
) {
    Surface(
        color = if (!loadingIsCompleted) LocalColorPalette.current.disabledColor else Color.Transparent,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = if (loadingIsCompleted) text else AnnotatedString(""),
            style = style ?: LocalTextStyle.current,
            fontWeight = fontWeight,
            textAlign = textAlign,
        )
    }
}

@Composable
fun BlurText(
    text: String,
    loadingIsCompleted: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle? = null,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
) {
    Surface(
        color = if (!loadingIsCompleted) LocalColorPalette.current.disabledColor else Color.Transparent,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = if (loadingIsCompleted) text else "",
            style = style ?: LocalTextStyle.current,
            fontWeight = fontWeight,
            textAlign = textAlign,
        )
    }
}