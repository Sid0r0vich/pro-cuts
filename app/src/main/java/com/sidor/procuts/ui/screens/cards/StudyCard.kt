package com.sidor.procuts.ui.screens.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StudyCard(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.border(BorderStroke(2.dp, Color.Gray)),
        onClick = onClick
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}