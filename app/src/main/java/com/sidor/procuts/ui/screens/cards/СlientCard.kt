package com.sidor.procuts.ui.screens.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.Client
import com.sidor.procuts.utils.getPainterFromByteArray

@Composable
fun ClientCard(
    client: Client
) {
    val defaultPhoto = painterResource(R.drawable.default_user_avatar)
    val clientPhoto: Painter = getPainterFromByteArray(client.photo) ?: defaultPhoto
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = clientPhoto,
            contentDescription = stringResource(R.string.client_ava),
            modifier = Modifier
                .weight(2f)
                .size(80.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = client.getFullName(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(3f)
        )
    }
}
