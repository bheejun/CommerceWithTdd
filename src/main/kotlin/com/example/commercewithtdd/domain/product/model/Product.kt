package com.example.commercewithtdd.domain.product.model

import com.example.commercewithtdd.domain.seller.model.Seller
import com.example.commercewithtdd.domain.user.model.User
import jakarta.persistence.*


@Entity
@Table(name = "product")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val detail: String,

    @Column(nullable = false)
    val category: String,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false)
    val quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    val seller: Seller,

    @Version
    val version: Long?= null

)
