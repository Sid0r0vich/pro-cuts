package com.sidor.procuts.ui.screens.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.sidor.procuts.data.Cut
import com.sidor.procuts.ui.screens.DefaultSpacer

@Composable
fun CutCard(
    cut: Cut,
) {
    Column {
        Text(
            text = "Cut: ${cut.name}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )
        DefaultSpacer(1)
        Text(
            text = "Description: ${cut.description}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )
        DefaultSpacer(1)
        Image(
            painter = painterResource(cut.imgId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}