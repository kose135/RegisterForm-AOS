package com.base.registerform.presentation.screen.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.base.registerform.presentation.navigation.MainNavigation

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val email = viewModel.email
    Log.e("MainScreen", "email = ${email}")

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Transparent)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    navController.navigate(MainNavigation.Register.route)
                }
        )

        Text(
            modifier = Modifier
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            text = "Hello!\n$email",
        )
    }
}
