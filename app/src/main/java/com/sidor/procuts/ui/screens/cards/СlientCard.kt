package com.sidor.procuts.ui.screens.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.ui.components.TextWithBoldField
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
    val clientPhoto: Painter = getPainterFromByteArray(clientDTO.photo) ?: painterResource(R.drawable.default_user_photo)

    Column {
        PersonCard(clientDTO.toPersonDTO())
        DefaultSpacer(3)
        DefaultCard {
            if (clientDTO.phoneNumber != null) {
                TextWithBoldField(
                    field = stringResource(R.string.client_phone_number),
                    value = formatDefault(clientDTO.phoneNumber),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
