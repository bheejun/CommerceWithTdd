package com.example.commercewithtdd.domain.seller.service

import com.example.commercewithtdd.domain.seller.dto.SellerLoginRequest
import com.example.commercewithtdd.domain.seller.dto.SellerSignUpRequest
import com.example.commercewithtdd.domain.seller.dto.UpdateInfoRequest

interface SellerService {


    fun sellerSignUp (sellerSignUpRequest: SellerSignUpRequest) : String

    fun sellerLogin (sellerLoginRequest: SellerLoginRequest) : String

    fun updateInfo (sellerName : String, updateInfoRequest: UpdateInfoRequest)

}