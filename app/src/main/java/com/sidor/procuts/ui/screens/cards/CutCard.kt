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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sidor.procuts.R
import com.sidor.procuts.data.Cut
import com.sidor.procuts.ui.TextWithBoldField
import com.sidor.procuts.ui.screens.DefaultSpacer

@Composable
fun CutCard(
    cut: Cut,
) {
    Column {
        TextWithBoldField(
            field = stringResource(R.string.cut_field),
            value = cut.name,
            style = MaterialTheme.typography.titleLarge,
        )
        DefaultSpacer(1)
        TextWithBoldField(
            field = stringResource(R.string.description_field),
            value = cut.description,
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