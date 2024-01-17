package com.example.commercewithtdd.domain.user.service

import com.example.commercewithtdd.domain.seller.repository.SellerRepository
import com.example.commercewithtdd.domain.user.dto.LoginRequest
import com.example.commercewithtdd.domain.user.dto.SignUpRequest
import com.example.commercewithtdd.domain.user.model.User
import com.example.commercewithtdd.domain.user.repository.UserRepository
import com.example.commercewithtdd.exception.InvalidPasswordException
import com.example.commercewithtdd.exception.UserNotFoundException
import com.example.commercewithtdd.exception.UsernameAlreadyExistsException
import com.example.commercewithtdd.util.JwtUtil
import jakarta.servlet.http.HttpServletResponse
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val sellerRepository: SellerRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val response: HttpServletResponse
) : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): String {
        val username = signUpRequest.username
        if (userRepository.existsByUsername(username) or sellerRepository.existsByUsername(username)) {
            throw UsernameAlreadyExistsException("Username is already exist")
        }

        userRepository.save(
            User(
                username = username,
                password =  passwordEncoder.encode(signUpRequest.password),
                address = signUpRequest.address

            )
        )

        return "Signup Complete"

    }

    @Transactional
    override fun login(loginRequest: LoginRequest): String {
        val username = loginRequest.username
        val password = loginRequest.password

        val token = jwtUtil.generateToken(username)

        val user = userRepository.findByUsername(username)
            .orElseThrow { UserNotFoundException("Not exist username") }

        if (!passwordEncoder.matches(password, user.password)) {
            throw InvalidPasswordException("Invalid password")
        }

        response.addHeader("Authorization", "Bearer $token")

        return token


    }

    @Transactional
    override fun updateAddress(username: String, newAddress : String){

        val user = userRepository.findByUsername(username).orElseThrow{ UserNotFoundException("Not exist username") }

        user.address = newAddress
        userRepository.save(user)
    }
}