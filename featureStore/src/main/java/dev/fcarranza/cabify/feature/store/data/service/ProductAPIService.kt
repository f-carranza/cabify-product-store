package dev.fcarranza.cabify.feature.store.data.service

import dev.fcarranza.cabify.feature.store.data.dto.ProductListResponse
import retrofit2.http.GET

interface ProductAPIService {

    @GET("palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getProducts(): ProductListResponse

}