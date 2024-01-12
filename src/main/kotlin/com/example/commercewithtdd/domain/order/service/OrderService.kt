package com.example.commercewithtdd.domain.order.service

interface OrderService {
    fun buy () : String
    fun cancel () : String
}