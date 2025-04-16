package com.sidor.procuts.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        onClick = onClick
    ) {
        Box(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun DateItem(
    modifier: Modifier = Modifier,
    date: Date,
    onClick: () -> Unit = {}
) {
    Item(
        modifier = modifier,
        text = SimpleDateFormat("dd MMMM yyyy").format(date),
        onClick = onClick
    )
}