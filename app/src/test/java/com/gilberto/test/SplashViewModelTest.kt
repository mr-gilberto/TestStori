package com.gilberto.test

import com.gilberto.domain.models.UserEntity
import com.gilberto.domain.usecase.GetUserUseCase
import com.gilberto.test.features.splash.SplashViewModel
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class SplashViewModelTest {


    private val mockGetUserUseCase = mock<GetUserUseCase>()
    private val user = mock<UserEntity>()

    private val viewModel = SplashViewModel(mockGetUserUseCase)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun observeUserInfo_success() = runTest {
        whenever(mockGetUserUseCase.invoke()).thenReturn(Result.success(user))
        viewModel.observeUserInfo()
        assertThat(viewModel.uiState.value.isLoggedIn, equalTo(true))
    }

    @Test
    fun observeUserInfo_failure() = runTest {
        whenever(mockGetUserUseCase.invoke()).thenReturn(Result.failure(Exception("Error")))
        viewModel.observeUserInfo()
        assertThat(viewModel.uiState.value.isLoggedIn, equalTo(false))
    }

}