package com.example.projeto1alexandre.usecases

import com.example.projeto1alexandre.data.model.Repo
import com.example.projeto1alexandre.data.repository.UserRepository
import com.example.projeto1alexandre.domain.usecase.GetUserReposUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUserReposUseCaseTest {

    private lateinit var getUserReposUseCase: GetUserReposUseCase
    private val mockRepository: UserRepository = mockk()

    @Before
    fun setUp() {
        getUserReposUseCase = GetUserReposUseCase(mockRepository)
    }

    @Test
    fun `invoke should return user details`() = runTest {
        // Arrange
        val username = "user1"
        val mockRepos = listOf(
            Repo(1, "repo1", "repo_url1", "descricao repo1"),
            Repo(1, "repo2", "repo_url2", null),
        )
        coEvery { mockRepository.getUserRepos(username) } returns mockRepos

        // Act
        val result = getUserReposUseCase(username)

        // Assert
        assertEquals(mockRepos, result)
    }
}