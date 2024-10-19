package com.example.projeto1alexandre.usecases

import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.data.repository.UserRepository
import com.example.projeto1alexandre.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private val mockRepository: UserRepository = mockk()

    @Before
    fun setUp() {
        getUsersUseCase = GetUsersUseCase(mockRepository)
    }

    @Test
    fun `invoke should return list of users`() = runTest {
        // Arrange
        val mockUsers = listOf(User("user1", 1, "bio1", "avatar_url1"))
        coEvery { mockRepository.getUsers() } returns mockUsers

        // Act
        val result = getUsersUseCase()

        // Assert
        assertEquals(mockUsers, result)
    }
}