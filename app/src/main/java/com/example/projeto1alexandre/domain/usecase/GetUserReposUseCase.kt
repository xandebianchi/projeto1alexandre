package com.example.projeto1alexandre.domain.usecase

import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.repository.UserRepository

class GetUserReposUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(username: String): List<Repo> {
        return repository.getUserRepos(username)
    }
}