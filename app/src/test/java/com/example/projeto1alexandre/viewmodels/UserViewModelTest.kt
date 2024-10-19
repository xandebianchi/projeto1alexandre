package com.example.projeto1alexandre.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.domain.usecase.GetUserDetailsUseCase
import com.example.projeto1alexandre.domain.usecase.GetUserReposUseCase
import com.example.projeto1alexandre.testutils.extensions.livedata.getOrAwaitValue
import com.example.projeto1alexandre.ui.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //@get:Rule
    //val mainDispatcherRule = MainDispatcherRule()

    private lateinit var userViewModel: UserViewModel
    private val getUserDetailsUseCase: GetUserDetailsUseCase = mockk()
    private val getUserReposUseCase: GetUserReposUseCase = mockk()

    @Before
    fun setup() {
        userViewModel = UserViewModel(getUserDetailsUseCase, getUserReposUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllData should post Pair of user and repos`() = runTest {
        // Arrange
        val username = "user1"
        val mockUser = User(username, 1, "bio1", "avatar_url1")
        val mockRepos = listOf(Repo(1, "repo1", "url1", "description1"))

        coEvery { getUserDetailsUseCase(username) } returns mockUser
        coEvery { getUserReposUseCase(username) } returns mockRepos

        val observer = mockk<Observer<Pair<User, List<Repo>>>>(relaxed = true)
        userViewModel.allDataReceived.observeForever(observer)

        // Act
        userViewModel.getAllData(username)

        // Simule a espera do coroutine
        advanceUntilIdle()

        val result = userViewModel.allDataReceived.getOrAwaitValue()

        // Assert
        assertEquals(Pair(mockUser, mockRepos), result)
        coVerify { getUserDetailsUseCase(username) }
        coVerify { getUserReposUseCase(username) }

        // Limpeza
        userViewModel.allDataReceived.removeObserver(observer)
    }
}