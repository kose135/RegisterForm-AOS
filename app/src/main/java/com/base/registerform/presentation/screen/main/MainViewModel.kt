package com.base.registerform.presentation.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    arguments: SavedStateHandle,
) : ViewModel() {
    var email by mutableStateOf("")

    init {
        email = arguments.get<String>("email") ?: ""
    }
}