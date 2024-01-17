package com.example.commercewithtdd.domain.product.dto

data class ProductInfoUpdateRequest(
        val productId : Long,
        val quantity : Int
)