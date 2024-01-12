package com.example.commercewithtdd.exception

class UsernameAlreadyExistsException(message: String) : RuntimeException(message)
class UserNotFoundException(message: String) : RuntimeException(message)
class InvalidPasswordException(message: String) : RuntimeException(message)
class UnauthorizedUserException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class PostNotFoundException(message: String) : RuntimeException(message)