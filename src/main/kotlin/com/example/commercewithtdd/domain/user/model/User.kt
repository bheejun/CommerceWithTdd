package com.example.commercewithtdd.domain.user.model

import com.example.commercewithtdd.domain.order.model.Order
import com.example.commercewithtdd.domain.product.model.Product
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? =null,

    @Column(nullable = false)
    val username : String,

    @Column(nullable = false)
    val password : String,

    @Column(nullable = false)
    var address : String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val favoriteList : MutableList<Product> ?= null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderList: MutableList<Order> ?= null,

    @Version
    val version: Long?= null
)

