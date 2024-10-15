package com.example.projeto1alexandre.data.repository

import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.data.remote.GitHubApi

class UserRepository(private val api: GitHubApi) {
    suspend fun getUsers(): List<User> = api.getUsers()

    suspend fun getUserDetails(username: String): User = api.getUserDetails(username)

    suspend fun getUserRepos(username: String): List<Repo> = api.getUserRepos(username)
}