package com.example.commercewithtdd.domain.seller.controller

import com.example.commercewithtdd.domain.seller.dto.SellerLoginRequest
import com.example.commercewithtdd.domain.seller.dto.SellerSignUpRequest
import com.example.commercewithtdd.domain.seller.dto.UpdateInfoRequest
import com.example.commercewithtdd.domain.seller.service.SellerService
import com.example.commercewithtdd.domain.user.dto.LoginRequest
import com.example.commercewithtdd.domain.user.dto.SignUpRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/seller")
class SellerController (private val sellerService: SellerService){
    @PostMapping("/signUp")
    fun signUp(@Valid @RequestBody sellerSignUpRequest: SellerSignUpRequest): ResponseEntity<String> {
        sellerService.sellerSignUp(sellerSignUpRequest)
        return ResponseEntity("Seller registered successfully", HttpStatus.OK)
    }
    @PostMapping("/login")
    fun login(@RequestBody sellerLoginRequest: SellerLoginRequest) : ResponseEntity<String> {
        sellerService.sellerLogin(sellerLoginRequest)
        return ResponseEntity("Login successfully", HttpStatus.OK)
    }

    @PutMapping("/updateInfo")
    fun updateInfo(@AuthenticationPrincipal seller : UserDetails, @RequestBody updateInfoRequest: UpdateInfoRequest) : ResponseEntity<String>{

        sellerService.updateInfo(seller.username, updateInfoRequest)
        return ResponseEntity("Update complete", HttpStatus.OK)
    }
}