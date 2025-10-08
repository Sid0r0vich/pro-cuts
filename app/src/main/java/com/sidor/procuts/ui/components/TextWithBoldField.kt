package com.sidor.procuts.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun TextWithBoldField(
    field: String,
    value: String,
    style: TextStyle = LocalTextStyle.current
) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$field: ")
        }
        append(value)
    }
    Text(
        text = text,
        style = style
    )
}

@Composable
fun BlurTextWithBoldField(
    loadingIsCompleted: Boolean,
    field: String,
    value: String,
    style: TextStyle = LocalTextStyle.current
) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$field: ")
        }
        append(value)
    }
    BlurText(
        loadingIsCompleted = loadingIsCompleted,
        text = text,
        style = style
    )
}
