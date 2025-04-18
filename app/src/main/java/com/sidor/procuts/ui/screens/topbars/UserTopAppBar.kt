package com.sidor.procuts.ui.screens.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.DpSpacer
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppBar(
    modifier: Modifier = Modifier,
    userName: String = stringResource(R.string.default_user_name),
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    DefaultTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DpSpacer(3)
                Image(
                    painter = painterResource(R.drawable.default_user_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                )
                DpSpacer(7)
                Text(
                    text = "Hello, $userName!",
                    style = MaterialTheme.typography.titleMedium,
                )
                DpSpacer(7)
                SearchBar(
                    modifier = Modifier.weight(1f),
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange
                )
                DpSpacer(7)
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = { })
                        .size(24.dp),
                    contentDescription = stringResource(id = R.string.icon_notifications)
                )
                DpSpacer(3)
            }
        },
    )
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(vertical = 0.dp)
            .clip(RoundedCornerShape(35.dp))
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (searchText.isEmpty()) {
                        Text(
                            text = "search",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                innerTextField()
            },
            modifier = Modifier.padding(horizontal = 15.dp),
            textStyle = MaterialTheme.typography.bodyLarge
        )
        Icon(
            imageVector = Icons.Outlined.Search,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(10.dp),
            contentDescription = stringResource(id = R.string.icon_search)
        )
    }
}