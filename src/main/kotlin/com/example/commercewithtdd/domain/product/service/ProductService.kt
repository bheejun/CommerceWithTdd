package com.example.commercewithtdd.domain.product.service

import com.example.commercewithtdd.domain.product.dto.ProductInfoUpdateRequest
import com.example.commercewithtdd.domain.product.dto.ProductRegistrationCancelRequest
import com.example.commercewithtdd.domain.product.dto.ProductRegistrationRequest

interface ProductService {

    fun registration (productRegistrationRequest: ProductRegistrationRequest, sellerName: String)

    fun updateProductInfo(productInfoUpdateRequest: ProductInfoUpdateRequest, sellerName: String)
    fun registrationCancel (productRegistrationCancelRequest: ProductRegistrationCancelRequest, sellerName: String)

}