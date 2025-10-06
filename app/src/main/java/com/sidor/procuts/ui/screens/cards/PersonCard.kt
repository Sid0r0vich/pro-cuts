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
import com.sidor.procuts.data.models.PersonDTO
import com.sidor.procuts.utils.getPainterFromByteArray
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun PersonCard(
    personDTO: PersonDTO
) {
    val personPhoto: Painter = personDTO.photo?.let {getPainterFromByteArray(
        personDTO.photo
    )} ?: painterResource(R.drawable.default_user_photo)

    DefaultCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = personPhoto,
                contentDescription = stringResource(R.string.client_ava),
                modifier = Modifier
                    .weight(2f)
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = personDTO.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(3f)
            )
        }
    }
}
