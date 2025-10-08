package com.sidor.procuts.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowKeyValue(
    field: String,
    value: String,
    style: TextStyle = LocalTextStyle.current
) {
    val fontSize = 17.sp

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$field:",
            style = style.copy(fontWeight = FontWeight.Bold, fontSize = fontSize),
            maxLines = Int.MAX_VALUE,
            overflow = TextOverflow.Visible,
            modifier = Modifier.weight(0.9f)
        )
        Text(
            text = value,
            style = style.copy(fontSize = fontSize),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(align = Alignment.End),
            maxLines = Int.MAX_VALUE,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun DottedLine(
    color: Color = Color.Gray,
    strokeWidth: Dp = 1.dp,
    dashLength: Float = 10f,
    gapLength: Float = 10f
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(strokeWidth)
    ) {
        val strokePx = strokeWidth.toPx()
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = strokePx,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength))
        )
    }
}