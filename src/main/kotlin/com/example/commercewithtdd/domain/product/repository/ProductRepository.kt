package com.example.commercewithtdd.domain.product.repository

import com.example.commercewithtdd.domain.product.model.Product
import com.example.commercewithtdd.domain.seller.model.Seller
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findAllBySeller( seller: Seller) : MutableList<Product>
    fun findAllBySellerUsername(username : String) : MutableList<Product>

}