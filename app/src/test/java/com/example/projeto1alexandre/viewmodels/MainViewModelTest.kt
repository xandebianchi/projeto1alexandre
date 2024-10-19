package com.example.projeto1alexandre.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.domain.usecase.GetUsersUseCase
import com.example.projeto1alexandre.ui.viewmodel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // Configura uma regra para que o LiveData seja executado instantaneamente
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock do use case
    private lateinit var getUsersUseCase: GetUsersUseCase

    // ViewModel a ser testado
    private lateinit var viewModel: MainViewModel

    // Observador mock para verificar mudanças no LiveData
    @RelaxedMockK
    private lateinit var observer: Observer<List<User>>

    @Before
    fun setup() {
        // Inicializa os mocks
        MockKAnnotations.init(this)

        // Cria um mock do use case
        getUsersUseCase = mockk(relaxed = true)

        // Inicializa o ViewModel com o mock do use case
        viewModel = MainViewModel(getUsersUseCase)
    }

    @Test
    fun `fetches users list and updates LiveData`() = runTest {
        // Dado que o use case retorna uma lista de usuários fictícia
        val usersList = listOf(
            User(login = "maria", id = 1, bio = "Dev", avatarUrl = "https://example.com/avatar1"),
            User(login = "joao", id = 2, bio = "Designer", avatarUrl = "https://example.com/avatar2")
        )
        coEvery { getUsersUseCase() } returns usersList

        // Adiciona um observador ao LiveData do ViewModel
        viewModel.users.observeForever(observer)

        // Quando a função para buscar usuários é chamada
        viewModel.getUsers()

        // Então verifica se o LiveData é atualizado com a lista de usuários correta
        verify { observer.onChanged(usersList) }
        assertEquals(usersList, viewModel.users.value)
    }
}