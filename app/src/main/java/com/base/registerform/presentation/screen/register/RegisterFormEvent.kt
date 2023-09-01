package com.base.registerform.presentation.screen.register

sealed class RegisterFormEvent {
    data class EmailChanged(val email: String) : RegisterFormEvent()
    data class PasswordChanged(val password: String) : RegisterFormEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegisterFormEvent()

    data class AcceptTerms(val isAccepted: Boolean) : RegisterFormEvent()

    object Submit: RegisterFormEvent()
}