package com.sidor.procuts.ui.screens.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.models.UserDTO
import com.sidor.procuts.ui.screens.DpSpacer
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.utils.getPainterFromByteArray
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalEncodingApi::class)
@Composable
fun UserTopAppBar(
    userDTO: UserDTO,
) {
    val userPhoto: Painter = userDTO.photo?.let {getPainterFromByteArray(
        Base64.decode(it)
    )} ?: painterResource(R.drawable.default_user_photo)

    DefaultTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                DpSpacer(13)
                Image(
                    painter = userPhoto,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, color = LocalColorPalette.current.mainColor, CircleShape),
                )
                DpSpacer(17)
                Text(
                    text = "${stringResource(R.string.hello)}, ${userDTO.name}!",
                    style = MaterialTheme.typography.titleLarge,
                    color = LocalColorPalette.current.mainColor
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    tint = LocalColorPalette.current.mainColor,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp),
                    contentDescription = stringResource(id = R.string.icon_settings),
                )
            }
        }
    )
}