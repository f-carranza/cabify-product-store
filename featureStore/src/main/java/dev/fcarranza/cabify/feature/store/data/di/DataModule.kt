package dev.fcarranza.cabify.feature.store.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.fcarranza.cabify.feature.store.data.datasource.DBBasketDatasource
import dev.fcarranza.cabify.feature.store.data.datasource.MockDiscountDatasource
import dev.fcarranza.cabify.feature.store.data.datasource.NetworkProductDatasource
import dev.fcarranza.cabify.feature.store.data.repository.BasketRepositoryImpl
import dev.fcarranza.cabify.feature.store.data.repository.DiscountRepositoryImpl
import dev.fcarranza.cabify.feature.store.data.repository.ProductRepositoryImpl
import dev.fcarranza.cabify.feature.store.data.service.ProductAPIService
import dev.fcarranza.cabify.feature.store.domain.repository.BasketRepository
import dev.fcarranza.cabify.feature.store.domain.repository.DiscountRepository
import dev.fcarranza.cabify.feature.store.domain.repository.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideProductAPIService(retrofit: Retrofit): ProductAPIService =
        retrofit.create(ProductAPIService::class.java)


    @Provides
    fun provideBasketDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("basket")
            }
        )
    }

    @Provides
    fun provideBasketDatasource(
        dataStore: DataStore<Preferences>,
        gson: Gson
    ): DBBasketDatasource {
        return DBBasketDatasource(dataStore, gson)
    }

    @Provides
    fun provideProductDatasource(
        productAPIService: ProductAPIService,
    ): NetworkProductDatasource = NetworkProductDatasource(productAPIService)


    @Provides
    fun provideDiscountDatasource(): MockDiscountDatasource =
        MockDiscountDatasource()

    @Provides
    @Singleton
    fun provideProductRepository(
        productDataSource: NetworkProductDatasource
    ): ProductRepository {
        return ProductRepositoryImpl(productDataSource)
    }

    @Provides
    @Singleton
    fun provideDiscountRepository(
        discountDatasource: MockDiscountDatasource
    ): DiscountRepository {
        return DiscountRepositoryImpl(discountDatasource)
    }

    @Provides
    @Singleton
    fun provideBasketRepository(
        basketDatasource: DBBasketDatasource
    ): BasketRepository {
        return BasketRepositoryImpl(basketDatasource)
    }

}