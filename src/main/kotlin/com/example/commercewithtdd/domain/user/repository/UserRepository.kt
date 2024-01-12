package com.example.commercewithtdd.domain.user.repository

import com.example.commercewithtdd.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsername (string: String) : Boolean
    fun findByUsername (string: String) : Optional<User>
}