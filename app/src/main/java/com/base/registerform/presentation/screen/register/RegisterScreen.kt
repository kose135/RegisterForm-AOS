package com.base.registerform.presentation.screen.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.base.registerform.presentation.navigation.MainNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val formState = viewModel.formState
    val validationState = viewModel.validationState.value

    when (validationState) {
        is ValidationState.Success -> {
            navController.navigate(
                MainNavigation.Main.moveRoute(validationState.email)
            )
        }

        is ValidationState.Failure -> {
            val context = LocalContext.current
            Toast.makeText(context, "This account is unavailable.", Toast.LENGTH_SHORT).show()
        }

        is ValidationState.Loading -> {
            CircularProgressIndicator()
        }

        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = formState.email,
            onValueChange = {
                viewModel.onEvent(RegisterFormEvent.EmailChanged(it))
            },
            isError = formState.emailError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        if (formState.emailError != null) {
            Text(
                text = formState.emailError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = formState.password,
            onValueChange = {
                viewModel.onEvent(RegisterFormEvent.PasswordChanged(it))
            },
            isError = formState.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (formState.passwordError != null) {
            Text(
                text = formState.passwordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = formState.repeatedPassword,
            onValueChange = {
                viewModel.onEvent(RegisterFormEvent.RepeatedPasswordChanged(it))
            },
            isError = formState.repeatedPasswordError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Repeat password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (formState.repeatedPasswordError != null) {
            Text(
                text = formState.repeatedPasswordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = formState.acceptedTerms,
                onCheckedChange = {
                    viewModel.onEvent(RegisterFormEvent.AcceptTerms(it))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Accept terms")
        }
        if (formState.termsError != null) {
            Text(
                text = formState.termsError,
                color = MaterialTheme.colorScheme.error,
            )
        }

        Button(
            onClick = {
                viewModel.onEvent(RegisterFormEvent.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}
