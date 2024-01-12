package com.example.commercewithtdd.domain.seller.repository

import com.example.commercewithtdd.domain.seller.model.Seller
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SellerRepository : JpaRepository<Seller, Long> {
    fun existsByUsername(string: String) : Boolean
    fun findByUsername(string: String) : Optional<Seller>
}