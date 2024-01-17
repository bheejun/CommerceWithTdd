package com.example.commercewithtdd.domain.order.model

import com.example.commercewithtdd.domain.product.model.Product
import com.example.commercewithtdd.domain.seller.model.Seller
import com.example.commercewithtdd.domain.user.model.User
import jakarta.persistence.*


@Entity
@Table(name = "order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? =null,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val productList : MutableList<Product> = mutableListOf(),

    @Column(nullable = false)
    var paymentStatus : Boolean ?= false,

    @Column(nullable = false)
    var deliveryStatus : String,

    @Column(nullable = false)
    var cancelStatus : Boolean ?= false,

    @Column(nullable = false)
    var changeRequestStatus : Boolean ?= false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    val user: User,


    @Version
    val version: Long?= null
)
