package com.example.commercewithtdd.domain.product.service

import com.example.commercewithtdd.domain.product.dto.ProductInfoUpdateRequest
import com.example.commercewithtdd.domain.product.dto.ProductRegistrationCancelRequest
import com.example.commercewithtdd.domain.product.dto.ProductRegistrationRequest
import com.example.commercewithtdd.domain.product.model.Product
import com.example.commercewithtdd.domain.product.repository.ProductRepository
import com.example.commercewithtdd.domain.seller.repository.SellerRepository
import com.example.commercewithtdd.exception.ProductNotFoundException
import com.example.commercewithtdd.exception.UnauthorizedUserException
import com.example.commercewithtdd.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
        private val productService: ProductService,
        private val productRepository: ProductRepository,
        private val sellerRepository: SellerRepository
        ) : ProductService {
    override fun registration(productRegistrationRequest: ProductRegistrationRequest, sellerName : String) {

        if(sellerRepository.existsByUsername(sellerName)){
            productRepository.save(
                    Product(
                            name = productRegistrationRequest.name,
                            detail = productRegistrationRequest.detail,
                            category = productRegistrationRequest.category,
                            price = productRegistrationRequest.price,
                            quantity = productRegistrationRequest.quantity,
                            seller = sellerRepository.findByUsername(sellerName).orElseThrow{
                                UserNotFoundException("Seller does not exist")
                            }
                    )
            )
        }else{
            throw UserNotFoundException("Seller does not exist")
        }

    }

    override fun updateProductInfo(productInfoUpdateRequest: ProductInfoUpdateRequest, sellerName: String) {
        val product = productRepository.findById(productInfoUpdateRequest.productId).orElseThrow{
            ProductNotFoundException("Product does not exist")
        }
        if(product.seller.username.equals(sellerName)){
            product.quantity = productInfoUpdateRequest.quantity
            productRepository.save(product)
        }else{
            throw UnauthorizedUserException("Only Seller who registered product can update")
        }
    }

    override fun registrationCancel(productRegistrationCancelRequest: ProductRegistrationCancelRequest, sellerName: String) {
        val product = productRepository.findById(productRegistrationCancelRequest.productId).orElseThrow {
            ProductNotFoundException("Product does Not exist")
        }
        if(product.seller.username.equals(sellerName)){
            productRepository.delete(product)
        }else{
            throw UnauthorizedUserException("Only Seller who registered product can delete")
        }
    }

}