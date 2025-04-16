package com.sidor.procuts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAppBar(
    modifier: Modifier = Modifier,
    userName: String = stringResource(R.string.default_user_name),
) {
    DefaultTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.padding(horizontal = 7.dp))
                Image(
                    painter = painterResource(R.drawable.default_user_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Gray, CircleShape),
                )
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(
                    text = "Hello, $userName!",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                SearchBar(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = {  })
                        .size(24.dp),
                    contentDescription = stringResource(id = R.string.icon_notifications)
                )
                Spacer(modifier = Modifier.padding(horizontal = 7.dp))
            }
        },
    )
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 7.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(color = Color.White),
    ) {
        TextField(
            value = "",
            onValueChange = { },
            placeholder = {
                Text(text = stringResource(id = R.string.search_placeholder))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        Icon(
            imageVector = Icons.Outlined.Search,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            contentDescription = stringResource(id = R.string.icon_search)
        )
    }
}