package com.sidor.procuts.ui.screens.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.ui.TextWithBoldField
import com.sidor.procuts.ui.screens.DefaultSpacer

@Composable
fun CutCard(
    cut: CutDTO,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TextWithBoldField(
                field = stringResource(R.string.cut_field),
                value = cut.name,
                style = MaterialTheme.typography.titleLarge,
            )
            DefaultSpacer(1)
            TextWithBoldField(
                field = stringResource(R.string.description_field),
                value = cut.description,
                style = MaterialTheme.typography.bodyLarge,
            )
            DefaultSpacer(1)
            Image(
                painter = painterResource(cut.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}