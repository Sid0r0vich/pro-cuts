package com.sidor.procuts.ui.screens.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.ui.TextWithBoldField
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.utils.getPainterFromByteArray

private fun formatDefault(digits: String): String {
    return when {
        digits.length < 10 -> digits
        else -> "8 (${digits.substring(0, 3)}) ${digits.substring(3, 6)}-${digits.substring(6, 8)}-${digits.substring(8,10)}"
    }
}

@Composable
fun ClientCard(
    clientDTO: ClientDTO,
) {
    val defaultPhoto = painterResource(R.drawable.default_user_avatar)
    val clientPhoto: Painter = getPainterFromByteArray(clientDTO.photo) ?: defaultPhoto
    Column {
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
                text = clientDTO.getFullName(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(3f)
            )
        }

        if (clientDTO.phoneNumber != null) {
            DefaultSpacer(3)
            TextWithBoldField(
                field = stringResource(R.string.client_phone_number),
                value = formatDefault(clientDTO.phoneNumber),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
