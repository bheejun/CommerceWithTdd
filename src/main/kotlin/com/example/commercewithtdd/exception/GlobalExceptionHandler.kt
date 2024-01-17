package com.example.commercewithtdd.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [UsernameAlreadyExistsException::class])
    fun handleUsernameAlreadyExistsException(ex: UsernameAlreadyExistsException): ResponseEntity<ErrorResponse> {
      val error = ErrorResponse(
          message = ex.message ?: "Username already exists",
          status = HttpStatus.BAD_REQUEST.value()
      )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handleUserNotFoundException(e: UserNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = e.message ?: "User not found",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [InvalidPasswordException::class])
    fun handleInvalidPasswordException(e: InvalidPasswordException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = e.message ?: "Invalid password",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [UnauthorizedUserException::class])
    fun handleUnauthorizedUserException(e: UnauthorizedUserException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = e.message ?: "Unauthorized user",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ProductNotFoundException::class])
    fun handelProductNotFoundException(e: ProductNotFoundException) : ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                message = e.message ?: "Product not found",
                status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}