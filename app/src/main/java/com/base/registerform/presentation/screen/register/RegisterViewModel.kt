package com.base.registerform.presentation.screen.register

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.registerform.domain.doOnFailure
import com.base.registerform.domain.doOnLoading
import com.base.registerform.domain.doOnSuccess
import com.base.registerform.domain.usecase.validation.PostValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val postValidationUseCase: PostValidationUseCase
) : ViewModel() {
    var formState by mutableStateOf(RegisterFormState())

    private val _validationState: MutableState<ValidationState> =
        mutableStateOf(ValidationState.Waiting)
    val validationState: State<ValidationState> = _validationState

    fun onEvent(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is RegisterFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is RegisterFormEvent.RepeatedPasswordChanged -> {
                formState = formState.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegisterFormEvent.AcceptTerms -> {
                formState = formState.copy(acceptedTerms = event.isAccepted)
            }

            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = Validation.email(formState.email)
        val passwordResult = Validation.password(formState.password)
        val repeatedPasswordResult = Validation.repeatPassword(
            formState.password, formState.repeatedPassword
        )
        val termsResult = Validation.terms(formState.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if (hasError) {
            formState = formState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            postValidationUseCase(formState.email, formState.password)
                .doOnSuccess {
                    Log.e("ViewModel", "doOnSuccess")
                    _validationState.value = ValidationState.Success(it)
                }
                .doOnFailure {
                    _validationState.value = ValidationState.Failure(it.localizedMessage!!)
                }
                .doOnLoading {
                    _validationState.value = ValidationState.Loading
                }.collect()
        }
    }
}