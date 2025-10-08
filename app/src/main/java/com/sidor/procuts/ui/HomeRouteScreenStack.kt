package com.sidor.procuts.ui

import com.sidor.procuts.ui.screens.screentypes.HomeScreenType

object HomeRouteScreenStack {
    var stack: MutableList<HomeScreenType> = mutableListOf()

    fun add(screen: HomeScreenType) {
        stack.add(screen)
    }

    fun remove(): HomeScreenType = stack.removeAt(stack.lastIndex)

    fun switch(screen: HomeScreenType) {
        stack[stack.lastIndex] = screen
    }

    fun clean() {
        stack = mutableListOf(HomeScreenType.Home)
    }
}