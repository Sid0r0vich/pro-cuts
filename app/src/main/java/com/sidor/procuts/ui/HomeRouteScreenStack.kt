package com.sidor.procuts.ui

import com.sidor.procuts.ui.screens.HomeScreen
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType

object HomeRouteScreenStack {
    var stack: MutableList<HomeScreenType> = mutableListOf(HomeScreenType.Home)

    fun add(screen: HomeScreenType) {
        stack.add(screen)
    }

    fun back(): HomeScreenType {
        stack.removeAt(stack.lastIndex)
        return stack[stack.lastIndex]
    }

    fun switch(screen: HomeScreenType) {
        stack[stack.lastIndex] = screen
    }

    fun clean() {
        stack = mutableListOf(HomeScreenType.Home)
    }
}