package dev.fcarranza.cabify.productstore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fcarranza.cabify.baselib.presentation.base.navigation.NavigationManager
import dev.fcarranza.cabify.feature.store.presentation.navigation.directions.GraphNavigation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager(GraphNavigation.default)
}