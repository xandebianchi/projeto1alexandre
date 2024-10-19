package com.example.projeto1alexandre.usecases

import com.example.projeto1alexandre.data.model.User
import com.example.projeto1alexandre.data.repository.UserRepository
import com.example.projeto1alexandre.domain.usecase.GetUserDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUserDetailsUseCaseTest {

    private lateinit var getUserDetailsUseCase: GetUserDetailsUseCase
    private val mockRepository: UserRepository = mockk()

    @Before
    fun setUp() {
        getUserDetailsUseCase = GetUserDetailsUseCase(mockRepository)
    }

    @Test
    fun `invoke should return user details`() = runTest {
        // Arrange
        val username = "user1"
        val mockUser = User(username, 1, "bio1", "avatar_url1")
        coEvery { mockRepository.getUserDetails(username) } returns mockUser

        // Act
        val result = getUserDetailsUseCase(username)

        // Assert
        assertEquals(mockUser, result)
    }
}