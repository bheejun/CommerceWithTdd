package com.example.commercewithtdd.domain.order.service

import com.example.commercewithtdd.domain.order.dto.OrderRequest
import com.example.commercewithtdd.domain.order.dto.OrderResponse
import com.example.commercewithtdd.domain.order.model.Order
import com.example.commercewithtdd.domain.order.repository.OrderRepository
import com.example.commercewithtdd.domain.product.model.Product
import com.example.commercewithtdd.domain.product.repository.ProductRepository
import com.example.commercewithtdd.domain.seller.repository.SellerRepository
import com.example.commercewithtdd.domain.user.repository.UserRepository
import com.example.commercewithtdd.exception.*
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
        private val productRepository: ProductRepository,
        private val userRepository: UserRepository,
        private val sellerRepository: SellerRepository,
        private val orderRepository: OrderRepository
) : OrderService {

    override fun makeOrder(orderRequest: OrderRequest, username: String){
        val user = userRepository.findByUsername(username).orElseThrow {
            UserNotFoundException("User does not exist")
        }

        val productList = makeProductList(orderRequest.productIdList)


        orderRepository.save(
                Order(
                        productList = productList,
                        user = user,
                        deliveryStatus = "WAITING"
                )
        )

    }

    override fun buy(orderId: Long, username: String) {
        val order = getOrderEntity(orderId)
        if(checkUser(username, order)){
            if(order.paymentStatus==false){
                order.paymentStatus = true
                orderRepository.save(order)
            }else{
                throw PaymentAlreadyCompletedException("Already paid order")

            }
        }else{
            throw UnauthorizedUserException("Only user who makes orderList can order complete")
        }

    }

    override fun getOrderListByUser(username: String): MutableList<Order> {
       val user = userRepository.findByUsername(username).orElseThrow {
           throw UserNotFoundException("User does not exist")
       }

        val orderList = user.orderList

        return orderList ?: throw OrderNotFoundException("Order does not exist")

    }

    override fun getOrderByUser(orderId: Long, username: String): OrderResponse {
        val order = getOrderEntity(orderId)
        if(checkUser(username, order)){
            return toResponse(order)

        }else{
            throw UnauthorizedUserException("Only user who makes this order, can check this order")
        }

    }

    override fun cancelByUser(orderId: Long, username: String) {
        val order = getOrderEntity(orderId)
        if (order.user.username != username) throw UnauthorizedUserException("User not authorized to cancel this order.")
        if (order.deliveryStatus in listOf("DELIVERED", "SHIPPED")) throw OrderCancellationException("Order cannot be cancelled now.")
        reverseProductQuantities(order)
        order.deliveryStatus = "CANCELLED"
        orderRepository.save(order)
    }

    override fun getOrderListByProductSeller(username: String): MutableList<Order> {
        val seller = sellerRepository.findByUsername(username).orElseThrow { SellerNotFoundException("Seller not found") }
        val sellerProducts = productRepository.findAllBySeller(seller)

        val sellerOrders = mutableListOf<Order>()
        for (product in sellerProducts) {
            val ordersWithProduct = orderRepository.findAll().filter { order ->
                order.productList.any { productInOrder -> productInOrder.id == product.id }
            }
            sellerOrders.addAll(ordersWithProduct)
        }

        return sellerOrders.distinct().toMutableList()
    }


    override fun updateOrderStatusBySeller(orderId: Long, productId: Long, newStatus: String, username: String) {
        val order = getOrderEntity(orderId)
        val product = productRepository.findById(productId).orElseThrow { ProductNotFoundException("Product does not exist") }

        if (product.seller.username != username) throw UnauthorizedUserException("Seller not authorized to update this order.")
        if (!order.productList.contains(product)) throw ProductNotInOrderException("Product not part of the order.")

        order.deliveryStatus = newStatus
        orderRepository.save(order)
    }



    private fun getOrderEntity(orderId: Long): Order {
        return orderRepository.findById(orderId).orElseThrow { OrderNotFoundException("Order does not exist") }
    }

    private fun reverseProductQuantities(order: Order) {
        order.productList.forEach { product ->
            val originalProduct = productRepository.findById(product.id!!).orElseThrow { ProductNotFoundException("Product does not exist") }
            originalProduct.quantity += 1
            productRepository.save(originalProduct)
        }
    }

    private fun makeProductList(productIdList: MutableList<Long>): MutableList<Product> {
        val productList = mutableListOf<Product>()
        for(id in productIdList){
            val product = productRepository.findById(id).orElseThrow {
                ProductNotFoundException("Product does not exist")
            }
            if (product.quantity >0){
                productList.add(product)
                product.quantity -= 1
                productRepository.save(product)

            }else{
                throw NotEnoughProductQuantityException("Product's quantity is zero")
            }



        }
        return productList
    }

    private fun checkUser (username: String, order: Order) : Boolean{
        return order.user.username == username
    }

    private fun toResponse (order : Order) : OrderResponse{

        return OrderResponse(
                productList = order.productList,
                deliveryStatus = order.deliveryStatus,
                paymentStatus = order.paymentStatus ?: false,
                username = order.user.username
        )

    }




}


