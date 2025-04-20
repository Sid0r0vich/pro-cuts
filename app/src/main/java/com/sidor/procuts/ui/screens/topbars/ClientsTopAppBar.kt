package com.sidor.procuts.ui.screens.topbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.DefaultSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientsTopAppBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onBack: () -> Unit
) {
    DefaultTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchBar(
                    modifier = Modifier.weight(1f),
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange
                )
                DefaultSpacer(1)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.button_back),
                    tint = MaterialTheme.colorScheme.primary
                )
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