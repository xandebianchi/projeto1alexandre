package com.example.projeto1alexandre.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.domain.usecase.GetUserDetailsUseCase
import com.example.projeto1alexandre.domain.usecase.GetUserReposUseCase
import com.example.projeto1alexandre.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {
    private val _userDetail = MutableLiveData<User>()
    val userDetail: LiveData<User> = _userDetail

    private val _repos = MutableLiveData<List<Repo>>()
    val repos: LiveData<List<Repo>> = _repos

    fun getUserDetail(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserDetailsUseCase(username)
            _userDetail.postValue(response)
        }
    }

    fun getUsersRepos(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserReposUseCase(username)
            _repos.postValue(response)
        }
    }
}