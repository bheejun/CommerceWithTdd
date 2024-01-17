package com.example.commercewithtdd.exception

class UsernameAlreadyExistsException(message: String) : RuntimeException(message)
class UserNotFoundException(message: String) : RuntimeException(message)
class InvalidPasswordException(message: String) : RuntimeException(message)
class UnauthorizedUserException(message: String) : RuntimeException(message)
class ProductNotFoundException(message: String) : RuntimeException(message)
class NotEnoughProductQuantityException(message: String) : RuntimeException(message)
class OrderNotFoundException(message: String) : RuntimeException(message)
class PaymentAlreadyCompletedException(message: String) : RuntimeException(message)
class OrderCancellationException(message: String) : RuntimeException(message)
class SellerNotFoundException(message: String) : RuntimeException(message)
class ProductNotInOrderException(message: String) : RuntimeException(message)