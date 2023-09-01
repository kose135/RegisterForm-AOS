package com.base.registerform.presentation.screen.register

sealed class ValidationState {
    data class Success(val email: String) : ValidationState()
    data class Failure(val message: String) : ValidationState()
    object Loading : ValidationState()
    object Waiting : ValidationState()
}