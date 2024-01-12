package com.example.commercewithtdd.domain.order.repository

import com.example.commercewithtdd.domain.order.model.Order
import com.example.commercewithtdd.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface OrderRepository : JpaRepository<Order, Long> {
    fun findByUserId (long: Long) : Optional<Long>
}