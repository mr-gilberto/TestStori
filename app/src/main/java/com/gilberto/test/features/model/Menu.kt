package com.gilberto.test.features.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import com.gilberto.test.R
import com.gilberto.test.util.appStringResource

data class Menu(
    val option: String,
    val type: MenuOption,
) {

    companion object {
        fun getMainMenu(): List<Menu> {
            return listOf(
                Menu(
                    appStringResource(R.string.logout),
                    MenuOption.LOG_OUT,
                ),
            )
        }

        fun getIcon(item: Menu) = when (item.type) {
            MenuOption.LOG_OUT -> Icons.Default.ExitToApp
        }
    }
}

enum class MenuOption {
    LOG_OUT,
}
