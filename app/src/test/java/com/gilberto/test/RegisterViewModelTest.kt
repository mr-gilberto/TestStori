package com.gilberto.test

import com.gilberto.domain.usecase.RegisterUseCase
import com.gilberto.domain.usecase.UpdateProfileUseCase
import com.gilberto.test.features.register.RegisterViewModel
import com.gilberto.test.features.register.RegistrationFormState
import com.gilberto.test.util.NetworkManager
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertNotNull
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
import org.mockito.kotlin.verifyNoInteractions

class RegisterViewModelTest {

    private val registerUseCase = mock<RegisterUseCase>()
    private val updateProfileUseCase = mock<UpdateProfileUseCase>()
    private val networkManager = mock<NetworkManager>()

    private val viewModel by lazy { RegisterViewModel(registerUseCase, updateProfileUseCase, networkManager) }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun register_success() {
        runTest {
            whenever(networkManager.isNetworkAvailable()).thenReturn(true)


            val validFormState = RegistrationFormState(
                name = "Juan",
                surname = "Perez",
                email = "Juan@gmail.com",
                password = "password",
                repeatedPassword = "password"
            )

            viewModel.register(validFormState)

            verify(registerUseCase).invoke(RegisterUseCase.Params("Juan@gmail.com", "password"))
            verify(updateProfileUseCase).invoke(UpdateProfileUseCase.Params("Juan Perez"))

            assertThat(viewModel.uiState.value.loading, equalTo(false))
            assertThat(viewModel.uiState.value.successRegister, equalTo(true))
            assertThat(viewModel.uiState.value.errorState, equalTo(null))
        }
    }


    @Test
    fun register_network_fail() = runTest {
        whenever(networkManager.isNetworkAvailable()).thenReturn(false)


        val validFormState = RegistrationFormState()
        viewModel.register(validFormState)

        assertThat(viewModel.uiState.value.loading, equalTo(false))
        assertThat(viewModel.uiState.value.successRegister, equalTo(false))
        assertNotNull(viewModel.uiState.value.errorState)

        verifyNoInteractions(registerUseCase)
        verifyNoInteractions(updateProfileUseCase)
    }


    @Test
    fun register_invalidForm() = runTest {
        val invalidFormState = RegistrationFormState(email = "")
        viewModel.register(invalidFormState)

        assertThat(viewModel.uiState.value.loading, equalTo(false))
        assertThat(viewModel.uiState.value.successRegister, equalTo(false))
        assertNotNull(viewModel.uiState.value.errorState)

        verifyNoInteractions(registerUseCase)
        verifyNoInteractions(updateProfileUseCase)
    }

    @Test
    fun updateProfile_success() = runTest {
        whenever(networkManager.isNetworkAvailable()).thenReturn(true)
        val validFormState = RegistrationFormState(
            name = "Juan",
            surname = "Perez",
            email = "Juan@gmail.com",
            password = "password",
            repeatedPassword = "password"
        )
        viewModel.register(validFormState)

        val expectedFullName = "Juan Perez"
        verify(updateProfileUseCase).invoke(UpdateProfileUseCase.Params(expectedFullName))

        assertThat(viewModel.uiState.value.loading, equalTo(false))
        assertThat(viewModel.uiState.value.successRegister, equalTo(true))
        assertThat(viewModel.uiState.value.errorState, equalTo(null))
    }

}