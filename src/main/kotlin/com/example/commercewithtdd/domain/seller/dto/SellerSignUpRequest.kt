package com.example.commercewithtdd.domain.seller.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SellerRequest(
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "Invalid username" )
    val sellerName : String,


    @Size(min = 4, max = 10, message = "Password must be between 4 and 10" )
    @Pattern(regexp = "^[a-zA-Z0-9!@#\$%^&*]+$", message = "Invalid password")
    val password : String,

    val bankAccount : String,

    val storeDetail : String
)