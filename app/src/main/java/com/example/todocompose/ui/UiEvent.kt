package com.example.todocompose.ui

sealed interface UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent
    data object NavigateBack: UiEvent
    data class Navigate< T:Any>(val route: T, val args: T? = null) : UiEvent
}