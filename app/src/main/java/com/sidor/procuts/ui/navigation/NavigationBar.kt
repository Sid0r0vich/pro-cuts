package com.sidor.procuts.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentScreen: NavDestination?,
    onClick: (Route) -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = LocalColorPalette.current.barColor,
    ) {
        TOP_LEVEL_DESTINATIONS
            .onEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = LocalColorPalette.current.mainColor
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.iconTextId),
                            color = LocalColorPalette.current.mainColor
                        )
                    },
                    selected = TOP_LEVEL_DESTINATIONS.map { item -> item.route.toString() }.indexOf(
                        currentScreen?.route
                    ) == index,
                    onClick = {
                        onClick(item.route)
                    }
                )
            }
    }
}