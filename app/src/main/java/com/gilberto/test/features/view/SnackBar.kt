package com.gilberto.test.features.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gilberto.test.R
import com.gilberto.test.util.appStringResource

@Composable
fun SnackBarBase(
    errorState: String,
    onDismiss: (() -> Unit)? = null,
) {
    Column {
        Snackbar(
            action = {
                if (onDismiss != null) {
                    Button(onClick = {
                        onDismiss.invoke()
                    }) {
                        Text(appStringResource(R.string.dismiss))
                    }
                }
            },
            modifier = Modifier.padding(8.dp),
        ) { Text(text = errorState) }
    }
}
