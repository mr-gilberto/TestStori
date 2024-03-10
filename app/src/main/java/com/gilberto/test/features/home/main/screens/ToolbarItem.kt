package com.gilberto.test.features.home.main.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.gilberto.test.R
import com.gilberto.test.theme.Colors
import com.gilberto.test.util.appStringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    appStringResource(R.string.movements),
                    color = Colors.ColorPrimary,
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                }) {
                    Icon(
                        Icons.Filled.Menu,
                        null,
                        tint = Colors.ColorPrimary,
                    )
                }
            },
            actions = {

            },
        )
    }
}
