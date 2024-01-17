package com.example.commercewithtdd.domain.order.dto

import com.example.commercewithtdd.domain.product.model.Product

data class OrderResponse(
        val productList : MutableList<Product>,
        val paymentStatus : Boolean,
        val deliveryStatus : String,
        val username : String

)
