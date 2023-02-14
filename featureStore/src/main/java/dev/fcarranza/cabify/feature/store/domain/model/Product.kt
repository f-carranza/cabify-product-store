package dev.fcarranza.cabify.feature.store.domain.model

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val currencyCode: String,
    val imageUrl: String,
    var discount: Discount? = null
)