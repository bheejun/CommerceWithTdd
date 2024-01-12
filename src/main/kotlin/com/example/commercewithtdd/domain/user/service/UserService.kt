package com.example.commercewithtdd.domain.user.service

import com.example.commercewithtdd.domain.user.dto.LoginRequest
import com.example.commercewithtdd.domain.user.dto.SignUpRequest
import com.example.commercewithtdd.domain.user.model.User

interface UserService {

    fun login (loginRequest: LoginRequest) : String
    fun signUp (signUpRequest: SignUpRequest ) : String
    fun updateAddress (username: String, newAddress : String)

}