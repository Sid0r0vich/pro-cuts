package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.models.UserDTO
import com.sidor.procuts.data.models.defaultPerson
import com.sidor.procuts.data.models.defaultUser
import com.sidor.procuts.ui.components.LocalBoardPadding
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.HomeCard
import com.sidor.procuts.ui.screens.screentypes.HomeCardScreenType
import com.sidor.procuts.ui.screens.topbars.UserTopAppBar
import com.sidor.procuts.ui.viewmodels.HomeViewModel
import com.sidor.procuts.ui.viewmodels.UserProfileViewModel


@Composable
fun HomeScreen(
    userDTO: UserDTO,
    onCardClick: (HomeCardScreenType) -> Unit,
) {
    TopAppBarScreen(
        topBar = {
            UserTopAppBar(userDTO)
        },
    ) {
        LazyPaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            item {
                Column {
                    HomeCard(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.clients),
                        onClick = { onCardClick(HomeCardScreenType.Clients) },
                        image = painterResource(R.drawable.clients)
                    )
                    DefaultSpacer(2)
                    HomeCard(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.start_cutting),
                        onClick = { onCardClick(HomeCardScreenType.StartCutting) },
                        image = painterResource(R.drawable.go_cut)
                    )
                    DefaultSpacer(2)
                    HomeCard(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.my_cuts),
                        onClick = { onCardClick(HomeCardScreenType.MyCuts) },
                        image = painterResource(R.drawable.archive)
                    )
                }
            }
        }
    }
}