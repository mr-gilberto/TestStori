package com.gilberto.test.features.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.rememberNavController
import com.gilberto.test.R
import com.gilberto.test.features.view.SnackBarBase
import com.gilberto.test.theme.AppTheme

@Composable
fun RegisterScreen(
    formState: RegistrationFormState = RegistrationFormState(),
    onEvent: (event: RegistrationFormEvent) -> Unit = {},
    isLoading: Boolean = false,
    errorState: ErrorState? = null,
) {
    Scaffold(
        snackbarHost = {
            errorState?.let { state ->
                val error = state.error ?: state.snackError?.let { stringResource(id = it) }
                if (error != null) {
                    SnackBarBase(error) {
                        onEvent.invoke(RegistrationFormEvent.OnSnackbarDismiss)
                    }
                }
            }
        },
        content = { innerPadding ->
            Box {
                Modifier.padding(innerPadding)
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(
                            15.dp,
                            60.dp,
                            15.dp,
                            0.dp,
                        ),
                ) {
                    Logo(
                        Modifier
                            .height(90.dp)
                            .fillMaxWidth(),
                    )
                    Inputs(Modifier.fillMaxWidth(), formState, onEvent)
                }

                if (isLoading) {
                    LoadingDialog()
                }
            }
        },
    )
}

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false,
        ),
    ) {
        Surface(
            modifier = Modifier
                .padding(10.dp)
                .size(48.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp)),
            color = Color.Transparent,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary, // Hace que el CircularProgressIndicator sea transparente para que el color de fondo del Surface se muestre
            )
        }
    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.register),
        contentDescription = null,
    )
}

@Composable
fun Inputs(
    modifier: Modifier,
    formState: RegistrationFormState,
    onEvent: (event: RegistrationFormEvent) -> Unit,
) {

    Column(modifier = modifier) {
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_controls),
                )
                .fillMaxWidth(),
            value = formState.name,
            onValueChange = { onEvent.invoke(RegistrationFormEvent.NameChange(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.name_input),
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                },
            ),
            label = { Text(text = stringResource(R.string.name_input)) },
        )


        OutlinedTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_controls),
                )
                .fillMaxWidth(),
            value = formState.surname,
            onValueChange = { onEvent.invoke(RegistrationFormEvent.SurnameChange(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.user_surname),
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                },
            ),
            label = { Text(text = stringResource(R.string.user_surname)) },
        )

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_controls),
                )
                .fillMaxWidth(),
            value = formState.email,
            onValueChange = { onEvent.invoke(RegistrationFormEvent.EmailChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.user_email),
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                },
            ),
            label = { Text(text = stringResource(R.string.user_email)) },
        )

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_controls),
                )
                .fillMaxWidth(),
            value = formState.password,
            onValueChange = { onEvent.invoke(RegistrationFormEvent.PasswordChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.password),
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
            ),
            label = { Text(text = stringResource(R.string.password)) },
        )

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_controls),
                )
                .fillMaxWidth(),
            value = formState.repeatedPassword,
            onValueChange = { onEvent.invoke(RegistrationFormEvent.RepeatedPasswordChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.password),
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
            ),
            label = { Text(text = stringResource(R.string.password_repeat)) },
        )

        ButtonLogIn(
            Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.default_margin),
                    top = dimensionResource(id = R.dimen.default_margin),
                    end = dimensionResource(id = R.dimen.default_margin),
                    bottom = 30.dp,
                ),
            onEvent,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ButtonLogIn(
    modifier: Modifier,
    onEvent: (event: RegistrationFormEvent) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier,
    ) {
        Button(
            onClick = {
                keyboardController?.hide()
                onEvent.invoke(RegistrationFormEvent.Register)
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_buttons),
                )
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val fakeNavController = rememberNavController()
    lateinit var viewModel: RegisterViewModel

    AppTheme {
        RegisterScreen()
    }
}
