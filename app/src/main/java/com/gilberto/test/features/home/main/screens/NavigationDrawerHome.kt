package com.gilberto.test.features.home.main.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gilberto.domain.models.UserEntity
import com.gilberto.test.R
import com.gilberto.test.features.model.Menu
import com.gilberto.test.theme.Colors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HeaderNavigationDrawer(
    modifier: Modifier = Modifier,
    user: UserEntity?,
) {
    Column(
        modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_person_24_white),
            contentDescription = null,
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .padding(bottom = 10.dp)
                .align(
                    Alignment.CenterHorizontally,
                ),
        )

        Text(
            text = user?.displayName.orEmpty(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium,
            color = Colors.ColorWhite,
        )
    }
}


@Composable
fun ItemNavigationDrawer(
    item: Menu,
    scope: CoroutineScope,
    selectedItem: MutableState<Menu>,
    drawerState: DrawerState,
    onItemMenuClick: (Menu) -> Unit,
) {
    NavigationDrawerItem(
        icon = { Icon(Menu.getIcon(item), contentDescription = null) },
        label = { Text(item.option) },
        selected = item == selectedItem.value,
        onClick = {
            onItemMenuClick.invoke(item)
            scope.launch { drawerState.close() }
            selectedItem.value = item
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
    )
}

