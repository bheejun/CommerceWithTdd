package com.example.commercewithtdd.domain.product.repository

import com.example.commercewithtdd.domain.product.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

}