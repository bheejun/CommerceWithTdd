package com.example.commercewithtdd.domain.order.service

import com.example.commercewithtdd.domain.order.dto.OrderRequest
import com.example.commercewithtdd.domain.order.dto.OrderResponse
import com.example.commercewithtdd.domain.order.model.Order
import com.example.commercewithtdd.domain.product.model.Product

interface OrderService {

    fun makeOrder(orderRequest: OrderRequest, username: String)
    fun buy(orderId: Long, username: String)
    fun getOrderByUser(orderId: Long, username: String): OrderResponse
    fun getOrderListByUser(username: String): MutableList<Order>
    fun cancelByUser(orderId: Long, username: String)

    fun getOrderListByProductSeller(username: String): MutableList<Order>
    fun updateOrderStatusBySeller(orderId: Long, productId: Long, newStatus: String, username: String)


}