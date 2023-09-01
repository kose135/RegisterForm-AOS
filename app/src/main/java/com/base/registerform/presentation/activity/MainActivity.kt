package com.base.registerform.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.base.registerform.presentation.navigation.MainNavigation
import com.base.registerform.presentation.screen.main.MainScreen
import com.base.registerform.presentation.screen.register.RegisterScreen
import com.base.registerform.ui.theme.LoginFormTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginFormTheme {
                val navHostController = rememberNavController()

                NavHost(
                    navController = navHostController,
                    startDestination = MainNavigation.Register.route
                ) {
                    composable(MainNavigation.Register.route) {
                        RegisterScreen(navController = navHostController)
                    }

                    composable(
                        MainNavigation.Main.route,
                        arguments = MainNavigation.Main.arguments
                    ) {
                        MainScreen(navController = navHostController)
                    }
                }

            }
        }
    }
}
