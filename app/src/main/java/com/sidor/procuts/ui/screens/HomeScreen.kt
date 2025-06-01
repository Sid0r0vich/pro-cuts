package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.LocalBoardPadding
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.HomeCard
import com.sidor.procuts.ui.screens.screentypes.HomeCardScreenType
import com.sidor.procuts.ui.screens.topbars.UserTopAppBar


@Composable
fun HomeScreen(
    onCardClick: (HomeCardScreenType) -> Unit
) {
    TopAppBarScreen(
        topBar = { UserTopAppBar() },
    ) {
        LazyPaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    HomeCard(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.clients),
                        onClick = { onCardClick(HomeCardScreenType.Clients) }
                    )
                    Spacer(modifier = Modifier.width(LocalBoardPadding.current * 2))
                    HomeCard(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.start_cutting),
                        onClick = { onCardClick(HomeCardScreenType.StartCutting) }
                    )
                }
            }
        }
    }
}