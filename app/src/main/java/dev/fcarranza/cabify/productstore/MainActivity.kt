package dev.fcarranza.cabify.productstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationCommand
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationManager
import dev.fcarranza.cabify.feature.store.presentation.StoreView
import dev.fcarranza.cabify.feature.store.presentation.basket.BasketCheckoutView
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation.checkout
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation.productDetailDialog
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation.store
import dev.fcarranza.cabify.feature.store.presentation.productdetail.ProductDialogView
import dev.fcarranza.cabify.productstore.presentation.theme.CabifyProductStoreTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            navigationManager.commands.collectAsState().value.also { command ->
                when {
                    command is NavigationCommand.GoBack -> {
                        navController.popBackStack()
                    }
                    command is NavigationCommand.Destination && command.destination.isNotEmpty() ->
                        navController.navigate(command.destination)
                }
            }
            CabifyProductStoreTheme {
                NavHost(
                    navController = navController,
                    startDestination = store.destination
                ) {
                    composable(route = store.destination) {
                        StoreView(hiltViewModel(it))
                    }
                    dialog(
                        route = productDetailDialog().route,
                        arguments = productDetailDialog().arguments,
                    ) {
                        ProductDialogView(
                            hiltViewModel(it)
                        )
                    }
                    composable(checkout.destination) {
                        BasketCheckoutView(hiltViewModel(it))
                    }
                }
            }
        }
    }
}