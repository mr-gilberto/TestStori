package com.gilberto.navigation

import androidx.navigation.NamedNavArgument

open class Route(
    var route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
)
