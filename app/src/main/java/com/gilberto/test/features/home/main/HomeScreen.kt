package com.gilberto.test.features.home.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.gilberto.domain.models.MovementEntity
import com.gilberto.test.R
import com.gilberto.test.features.home.main.screens.HeaderNavigationDrawer
import com.gilberto.test.features.home.main.screens.ItemNavigationDrawer
import com.gilberto.test.features.home.main.screens.ItemService
import com.gilberto.test.features.home.main.screens.ToolBar
import com.gilberto.test.features.model.Menu
import com.gilberto.test.features.view.SnackBarBase
import com.gilberto.test.theme.AppTheme
import com.gilberto.test.theme.Colors
import com.gilberto.test.views.Dialogs
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    viewState: HomeState = HomeState(),
    onItemMenuClick: (Menu) -> Unit = {},
    onMovementDetail: (MovementEntity) -> Unit = {},
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = Menu.getMainMenu()
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                Modifier
                    .width(250.dp)
                    .fillMaxHeight()
                    .background(Color.White, RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)),
            ) {
                HeaderNavigationDrawer(
                    Modifier
                        .fillMaxWidth()
                        .background(Colors.ColorAccent)
                        .padding(top = 30.dp, bottom = 20.dp),
                    viewState.user,
                )

                Spacer(Modifier.height(12.dp))
                Column(modifier = Modifier.fillMaxHeight()) {
                    Menu.getMainMenu().forEach { item ->
                        ItemNavigationDrawer(item, scope, selectedItem, drawerState, onItemMenuClick)
                    }
                }
            }
        },
        content = {
            MainContent(
                scope,
                drawerState,
                viewState.movements,
                viewState.snackBarMessage,
                onMovementDetail,
            )

            if (viewState.loading) {
                Dialogs.IndeterminateProgressDialog()
            }
        },
    )
}

@Composable
fun MainContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    movements: List<MovementEntity>,
    snackBarMessage: String?,
    onMovementDetail: (MovementEntity) -> Unit,
) {
    Scaffold(
        snackbarHost = {
            snackBarMessage?.let {
                SnackBarBase(it)
            }
        },
        topBar = { ToolBar(scope, drawerState) },
        content = { innerPadding ->
            ListServices(
                Modifier,
                innerPadding,
                movements,
                onMovementDetail,
            )
        },
    )
}

@Composable
fun ListServices(
    modifier: Modifier,
    paddings: PaddingValues,
    orderServices: List<MovementEntity>,
    onMovementClick: (MovementEntity) -> Unit,
) {
    Box(
        modifier,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 90.dp),
        ) {
            if (orderServices.isEmpty()) {
                item {
                    EmptyList()
                }
            } else {
                items(orderServices.size) { index ->
                    ItemService(
                        orderServices[index],
                        onItemClick = {
                            onMovementClick.invoke(orderServices[index])
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .height(54.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_no_task),
                contentDescription = null,
                modifier = Modifier.size(180.dp),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.no_movements),
                style = MaterialTheme.typography.titleLarge,
                color = Colors.ColorFastlaneBackground,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val fakeNavController = rememberNavController()
    AppTheme {
        HomeScreen()
    }
}