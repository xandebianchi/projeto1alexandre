package com.example.projeto1alexandre.domain.usecase

import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.data.repository.UserRepository

class GetUserDetailsUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(username: String): User {
        return repository.getUserDetails(username)
    }
}