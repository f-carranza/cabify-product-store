package dev.fcarranza.cabify.feature.store.data.mapper

import dev.fcarranza.cabify.feature.store.data.dto.ProductResponse
import dev.fcarranza.cabify.feature.store.domain.model.Product

interface ProductMapper {
    fun ProductResponse.toProduct(): Product {
        return Product(
            code = code,
            name = name,
            price = price,
            currencyCode = "EUR",
            imageUrl = getFakeImageUrl(code),
        )
    }

    fun getFakeImageUrl(code: String): String {
        return when (code) {
            "MUG" -> return "https://i.ibb.co/3YN6F8d/mug.jpg"
            "TSHIRT" -> return "https://i.ibb.co/8NqzY73/tshirt.jpg"
            "VOUCHER" -> return "https://i.ibb.co/ZBV1J81/giftvoucher.png"
            else -> "https://www.sidn.es/images/logos_vtem/cabify.jpg"
        }
    }


}