package com.sidor.procuts.ui.screens.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.DpSpacer
import com.sidor.procuts.ui.theme.LocalColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppBar(
    modifier: Modifier = Modifier,
    userName: String = stringResource(R.string.default_user_name),
) {
    DefaultTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                DpSpacer(13)
                Image(
                    painter = painterResource(R.drawable.default_user_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, color = LocalColorPalette.current.mainColor, CircleShape),
                )
                DpSpacer(17)
                Text(
                    text = "Hello, $userName!",
                    style = MaterialTheme.typography.titleLarge,
                    color = LocalColorPalette.current.mainColor
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    tint = LocalColorPalette.current.mainColor,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp),
                    contentDescription = stringResource(id = R.string.icon_notifications),
                )
            }
        }
    )
}