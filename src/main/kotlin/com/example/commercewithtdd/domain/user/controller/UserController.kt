package com.example.commercewithtdd.domain.user.controller

import com.example.commercewithtdd.domain.user.dto.LoginRequest
import com.example.commercewithtdd.domain.user.dto.SignUpRequest
import com.example.commercewithtdd.domain.user.dto.UpdateAddressRequest
import com.example.commercewithtdd.domain.user.model.User
import com.example.commercewithtdd.domain.user.service.UserService
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
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @PostMapping("/signUp")
    fun signUp(@Valid @RequestBody signupRequest: SignUpRequest): ResponseEntity<String> {
        userService.signUp(signupRequest)
        return ResponseEntity("User registered successfully", HttpStatus.OK)
    }
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest) : ResponseEntity<String>{
        userService.login(loginRequest)
        return ResponseEntity("Login Successfully", HttpStatus.OK)
    }

    @PutMapping("/updateAddress")
    fun updateAddress(@AuthenticationPrincipal user: UserDetails, @RequestBody updateAddressRequest : UpdateAddressRequest) : ResponseEntity<String> {
        val username = user.username
        userService.updateAddress(username, updateAddressRequest.newAddress)
        return ResponseEntity("Update successfully", HttpStatus.OK)

    }
}