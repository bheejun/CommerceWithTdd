package com.example.commercewithtdd.domain.product.controller

import com.example.commercewithtdd.domain.product.dto.ProductInfoUpdateRequest
import com.example.commercewithtdd.domain.product.dto.ProductRegistrationCancelRequest
import com.example.commercewithtdd.domain.product.dto.ProductRegistrationRequest
import com.example.commercewithtdd.domain.product.service.ProductService
import com.example.commercewithtdd.domain.seller.model.Seller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController (private val productService: ProductService){

    @PostMapping("/registration")
    fun registration(@AuthenticationPrincipal seller: UserDetails,
                     @RequestBody productRegistrationRequest: ProductRegistrationRequest) : ResponseEntity<String>{
        val sellerName = seller.username
        productService.registration(productRegistrationRequest, sellerName)
        return ResponseEntity("Registration complete", HttpStatus.OK)

    }

    @PutMapping("/updateInfo")
    fun updateInfo(@AuthenticationPrincipal seller: UserDetails, @RequestBody productInfoUpdateRequest: ProductInfoUpdateRequest){

    }

    @DeleteMapping("/cancel")
    fun registrationCancel(@AuthenticationPrincipal seller: UserDetails,
                           @RequestBody productRegistrationCancelRequest: ProductRegistrationCancelRequest){
        val sellerName = seller.username
        productService.registrationCancel(productRegistrationCancelRequest, sellerName)
    }


}