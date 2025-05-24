package com.sidor.procuts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R


@Composable
fun CameraClaimScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    DefaultCutQuestionnaireScreen(
        onBack = onBack,
        onNext = onNext,
    ) {
        Text(
            text = stringResource(R.string.camera_claim),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        DefaultSpacer(2)
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Image(
                painter = painterResource(R.drawable.face_id),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}