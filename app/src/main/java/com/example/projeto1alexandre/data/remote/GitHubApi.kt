package com.example.projeto1alexandre.data.remote

import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): User

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<Repo>
}