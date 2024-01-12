package com.example.commercewithtdd.domain.seller.model

import com.example.commercewithtdd.domain.product.model.Product
import jakarta.persistence.*

@Entity
@Table(name = "seller")
data class Seller(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val username : String,

    @Column(nullable = false)
    val password : String,

    @Column(nullable = false)
    var bankAccount : String,

    @Column(nullable = false)
    var storeInfo : String,

    @OneToMany(mappedBy = "seller", cascade = [CascadeType.ALL], orphanRemoval = true)
    val productList : MutableList<Product> = mutableListOf()

)
