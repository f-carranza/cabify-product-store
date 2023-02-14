package dev.fcarranza.cabify.baselib.presentation.base.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager(navigationCommand: NavigationCommand) {

    var commands = MutableStateFlow(navigationCommand)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}