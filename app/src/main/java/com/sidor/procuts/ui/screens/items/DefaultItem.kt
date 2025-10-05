package com.sidor.procuts.ui.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sidor.procuts.ui.components.BlurText

@Composable
fun DefaultItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    loadingIsCompleted: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            BlurText(
                text = text,
                loadingIsCompleted = loadingIsCompleted,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
