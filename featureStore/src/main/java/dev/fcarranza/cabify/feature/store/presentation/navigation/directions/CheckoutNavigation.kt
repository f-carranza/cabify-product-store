package dev.fcarranza.cabify.feature.store.presentation.navigation.directions

import androidx.navigation.NavType
import androidx.navigation.navArgument
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationCommand

object GraphNavigation {

    val checkout = NavigationCommand.Destination(destination = "checkout")

    const val KEY_PRODUCT_ID = "productId"

    fun productDetailDialog(productId: String = "") =
        NavigationCommand.Destination(arguments = listOf(navArgument(KEY_PRODUCT_ID) {
            type = NavType.StringType
        }),
            destination = "productDetail/$productId", route = "productDetail/{$KEY_PRODUCT_ID}"
        )
    val default = NavigationCommand.Destination(destination = "")

    val store =  NavigationCommand.Destination(destination = "store")

}