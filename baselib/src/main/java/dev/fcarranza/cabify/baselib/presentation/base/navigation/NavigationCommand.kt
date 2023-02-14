package dev.fcarranza.cabify.baselib.presentation.base.navigation

import androidx.navigation.NamedNavArgument
 open class NavigationCommand {
    class Destination(val destination: String, val route:String = "", val arguments: List<NamedNavArgument> = emptyList(),) : NavigationCommand()
    class GoBack : NavigationCommand()
}