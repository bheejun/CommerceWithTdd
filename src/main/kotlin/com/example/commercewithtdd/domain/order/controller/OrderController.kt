package com.example.commercewithtdd.domain.order.controller

import com.example.commercewithtdd.domain.order.dto.OrderRequest
import com.example.commercewithtdd.domain.order.dto.OrderResponse
import com.example.commercewithtdd.domain.order.model.Order
import com.example.commercewithtdd.domain.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController( private val orderService: OrderService) {
    @PostMapping("/make")
    fun makeOrder (@AuthenticationPrincipal user : UserDetails, @RequestBody orderRequest: OrderRequest) :ResponseEntity<String>{
        val username = user.username
        orderService.makeOrder(orderRequest, username)
        return ResponseEntity("Make order Complete",HttpStatus.OK)

    }

    @GetMapping
    fun getOrderList(@AuthenticationPrincipal user: UserDetails) : ResponseEntity<MutableList<Order>>{
        val username = user.username

        return ResponseEntity(orderService.getOrderListByUser(username), HttpStatus.OK)
    }

    @GetMapping("/{orderId}")
    fun getOrder(@AuthenticationPrincipal user: UserDetails, @PathVariable orderId: Long) : ResponseEntity<OrderResponse>{
        val username = user.username

        return ResponseEntity(orderService.getOrderByUser(orderId, username), HttpStatus.OK)
    }

    @PostMapping("/buy/{orderId}")
    fun buyOrder(@AuthenticationPrincipal user: UserDetails, @PathVariable orderId: Long): ResponseEntity<String> {
        val username = user.username
        orderService.buy(orderId, username)
        return ResponseEntity("Order purchase completed", HttpStatus.OK)
    }

    @PostMapping("/cancel/{orderId}")
    fun cancelOrderByUser(@AuthenticationPrincipal user: UserDetails, @PathVariable orderId: Long): ResponseEntity<String> {
        val username = user.username
        orderService.cancelByUser(orderId, username)
        return ResponseEntity("Order cancellation completed", HttpStatus.OK)
    }

    @GetMapping("/seller/orders")
    fun getOrderListByProductSeller(@AuthenticationPrincipal user: UserDetails): ResponseEntity<MutableList<Order>> {
        val username = user.username
        return ResponseEntity(orderService.getOrderListByProductSeller(username), HttpStatus.OK)
    }

    @PostMapping("/seller/update/{orderId}/{productId}")
    fun updateOrderStatusBySeller(
            @AuthenticationPrincipal user: UserDetails,
            @PathVariable orderId: Long,
            @PathVariable productId: Long,
            @RequestParam newStatus: String
    ): ResponseEntity<String> {
        val username = user.username
        orderService.updateOrderStatusBySeller(orderId, productId, newStatus, username)
        return ResponseEntity("Order status updated", HttpStatus.OK)
    }

}