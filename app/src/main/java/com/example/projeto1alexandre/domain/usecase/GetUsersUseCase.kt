package com.example.projeto1alexandre.domain.usecase

import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.data.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}