package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sidor.procuts.R
import com.sidor.procuts.data.caresList
import com.sidor.procuts.data.cutsList
import com.sidor.procuts.ui.ClientCard
import com.sidor.procuts.ui.DateItem
import com.sidor.procuts.ui.LocalGridPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(
    title: String,
    onBack: () -> Unit,
) {
        TopAppBarScreen(
            topBar = {
                ClientAppBar(
                    title = stringResource(R.string.client_tab_app_bar),
                    navigationIconContent = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.button_back),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                )
            },
        ) {
            LazyPaddingScreen(
                horizontalSpaceCount = 4,
                verticalSpaceCount = 2,
            ) {
                item {
                    DefaultSpacer(1)
                    ClientCard(title)
                }
                item {
                    DefaultSpacer(2)
                    Text(
                        text = stringResource(R.string.cuts),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
                cutsList.forEach { date ->
                    item {
                        DefaultSpacer(1)
                        DateItem(date = date, onClick = {})
                    }
                }
                item {
                    DefaultSpacer(2)
                    Text(
                        text = stringResource(R.string.cares),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
                caresList.forEach { date ->
                    item {
                        DefaultSpacer(1)
                        DateItem(date = date, onClick = {})
                    }
                }
            }
        }
}