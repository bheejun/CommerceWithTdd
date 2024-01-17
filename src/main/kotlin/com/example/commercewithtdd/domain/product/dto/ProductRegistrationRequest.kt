package com.example.commercewithtdd.domain.product.dto

data class ProductRegistrationRequest(
        val name : String,
        val detail : String,
        val category: String,
        val price : Double,
        val quantity : Int

)
