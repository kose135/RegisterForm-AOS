package com.base.registerform.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class MainNavigation(
    val route: String
) {
    object Register : MainNavigation("register")

    object Main : MainNavigation("main/{email}") {
        val arguments = listOf<NamedNavArgument>(
            navArgument("email") { type = NavType.StringType }
        )

        fun moveRoute(email: String): String {
            return "main/$email"
        }
    }
}