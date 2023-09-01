package com.base.registerform.presentation.screen.register

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)