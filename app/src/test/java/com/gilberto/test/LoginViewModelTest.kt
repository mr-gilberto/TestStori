package com.gilberto.test

import com.gilberto.domain.models.UserEntity
import com.gilberto.domain.usecase.LoginUseCase
import com.gilberto.test.features.login.LoginViewModel
import com.gilberto.test.util.NetworkManager
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.MockKAnnotations
import junit.framework.TestCase
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

class LoginViewModelTest {

    private val mockLoginUseCase = mock<LoginUseCase>()
    private val mockNetworkManager = mock<NetworkManager>()
    private val viewModel = LoginViewModel(mockLoginUseCase, mockNetworkManager)
    private val user = mock<UserEntity>()


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
    fun login_success() = runTest {
        whenever(mockNetworkManager.isNetworkAvailable()).thenReturn(true)

        val userName = "gilberto.ibarra@gmail.com"
        val userPassword = "password12"
        val params = LoginUseCase.Params(userName.trim(), userPassword.trim())
        whenever(mockLoginUseCase.invoke(params)).thenReturn(Result.success(user))

        viewModel.login(userName, userPassword)

        assertThat(viewModel.uiState.value.loading, equalTo(false))
        assertThat(viewModel.uiState.value.navigateToMainView, equalTo(true))
    }


    @Test
    fun login_fail() = runTest {
        val userName = "gilberto.ibarra@gmail.com"
        val userPassword = "password12"

        val params = LoginUseCase.Params(userName.trim(), userPassword.trim())
        whenever(mockNetworkManager.isNetworkAvailable()).thenReturn(true)
        whenever(mockLoginUseCase.invoke(params)).thenReturn(Result.failure(Exception("error")))


        viewModel.login(userName, userPassword)
        verify(mockLoginUseCase).invoke(params)

        assertThat(viewModel.uiState.value.loading, equalTo(false))
        assertThat(viewModel.uiState.value.navigateToMainView, equalTo(false))
        TestCase.assertNotNull(viewModel.uiState.value.errorState)
    }
}