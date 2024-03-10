package com.gilberto.test.features.login

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun LoginScreen(
    isLoading: Boolean = false,
    errorState: ErrorState? = null,
    onActionSnackBar: () -> Unit = {},
    onLogIn: (email: String, password: String) -> Unit,
    onRegister: () -> Unit,
    ) {
    Scaffold(
        snackbarHost = {
            errorState?.let { state ->
                if (state.snackError != null) {
                  SnackBarBase(stringResource(id = state.snackError), onActionSnackBar)
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
                    Inputs(Modifier.fillMaxWidth(), onLogIn, onRegister)
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
        painter = painterResource(id = R.drawable.workers),
        contentDescription = null,
    )
}

@Composable
fun Inputs(modifier: Modifier, onLogIn: (email: String, password: String) -> Unit, onRegister: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("ibtz.gilbert@gmail.com") }
    var password by rememberSaveable { mutableStateOf("password12") }

    Column(modifier = modifier) {
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
                    top = 30.dp,
                )
                .fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
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
            value = password,
            onValueChange = { password = it },
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

        BottomButtons(
            Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.default_margin),
                    top = dimensionResource(id = R.dimen.default_margin),
                    end = dimensionResource(id = R.dimen.default_margin),
                    bottom = 30.dp,
                ),
            onLogIn,
            onRegister,
            email,
            password,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomButtons(modifier: Modifier, onLogIn: (email: String, password: String) -> Unit, onRegister: () -> Unit, email: String, password: String) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier,
    ) {
        Button(
            onClick = {
                keyboardController?.hide()
                onLogIn.invoke(email, password)
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_buttons),
                )
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.log_in),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Button(
            onClick = {
                keyboardController?.hide()
                onRegister.invoke()
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
    lateinit var viewModel: LoginViewModel

    AppTheme {

    }
}
