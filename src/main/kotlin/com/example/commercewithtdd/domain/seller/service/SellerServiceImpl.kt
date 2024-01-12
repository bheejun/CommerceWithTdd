package com.example.commercewithtdd.domain.seller.service

import com.example.commercewithtdd.domain.seller.dto.SellerLoginRequest
import com.example.commercewithtdd.domain.seller.dto.SellerSignUpRequest
import com.example.commercewithtdd.domain.seller.dto.UpdateInfoRequest
import com.example.commercewithtdd.domain.seller.model.Seller
import com.example.commercewithtdd.domain.seller.repository.SellerRepository
import com.example.commercewithtdd.exception.InvalidPasswordException
import com.example.commercewithtdd.exception.UserNotFoundException
import com.example.commercewithtdd.exception.UsernameAlreadyExistsException
import com.example.commercewithtdd.util.JwtUtil
import jakarta.servlet.http.HttpServletResponse
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SellerServiceImpl(
    private val sellerRepository: SellerRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val response: HttpServletResponse
) : SellerService{
    @Transactional
    override fun sellerSignUp(sellerSignUpRequest: SellerSignUpRequest): String {
        val sellerName = sellerSignUpRequest.sellerName
        if (sellerRepository.existsByUsername(sellerName)) {
            throw UsernameAlreadyExistsException("SellerName is already exist")
        }

        sellerRepository.save(
            Seller(
                username = sellerName,
                password =  passwordEncoder.encode(sellerSignUpRequest.password),
                bankAccount = sellerSignUpRequest.bankAccount,
                storeInfo = sellerSignUpRequest.storeDetail

            )
        )

        return "SignUp Complete"

    }

    @Transactional
    override fun sellerLogin(sellerLoginRequest: SellerLoginRequest): String {
        val sellerName = sellerLoginRequest.sellerName
        val password = sellerLoginRequest.password

        val token = jwtUtil.generateToken(sellerName)

        val user = sellerRepository.findByUsername(sellerName)
            .orElseThrow { UserNotFoundException("Not exist sellerName") }

        if (!passwordEncoder.matches(password, user.password)) {
            throw InvalidPasswordException("Invalid password")
        }

        response.addHeader("Authorization", "Bearer $token")

        return token


    }

    @Transactional
    override fun updateInfo(sellerName : String, updateInfoRequest: UpdateInfoRequest) {
        val seller = sellerRepository.findByUsername(sellerName).orElseThrow{UserNotFoundException("Not exist sellerName")}

        seller.bankAccount = updateInfoRequest.newBankAccount
        seller.storeInfo = updateInfoRequest.newStoreDetail

        sellerRepository.save(seller)

    }
}