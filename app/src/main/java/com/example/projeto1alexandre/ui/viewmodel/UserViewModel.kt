package com.example.projeto1alexandre.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.domain.usecase.GetUserDetailsUseCase
import com.example.projeto1alexandre.domain.usecase.GetUserReposUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {
    private val _allDataReceived = MutableLiveData<Pair<User, List<Repo>>>()
    val allDataReceived: LiveData<Pair<User, List<Repo>>> = _allDataReceived

    fun getAllData(
        username: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response1 = async {
                getUserDetailsUseCase(username)
            }
            val response2 = async {
                getUserReposUseCase(username)
            }

            awaitAll(response1, response2)

            val responseAwait1 = response1.await()
            val responseAwait2 = response2.await()

            _allDataReceived.postValue(
                Pair(
                    responseAwait1,
                    responseAwait2
                ),
            )
        }
    }
}