package com.example.projeto1alexandre.di

import com.example.projeto1alexandre.data.remote.GitHubApi
import com.example.projeto1alexandre.data.repository.UserRepository
import com.example.projeto1alexandre.domain.usecase.GetUserDetailsUseCase
import com.example.projeto1alexandre.domain.usecase.GetUserReposUseCase
import com.example.projeto1alexandre.domain.usecase.GetUsersUseCase
import com.example.projeto1alexandre.ui.viewmodel.MainViewModel
import com.example.projeto1alexandre.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
    single { UserRepository(get()) }
    factory { GetUsersUseCase(get()) }
    factory { GetUserDetailsUseCase(get()) }
    factory { GetUserReposUseCase(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { UserViewModel(get(), get()) }
}