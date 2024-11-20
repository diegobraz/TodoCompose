package com.example.todocompose.ui.feature.addEdit

sealed interface AddEditEvent {
    data class TitleChanged(val title: String) : AddEditEvent
    data class DescriptionChanged(val description: String) : AddEditEvent
    data object Save: AddEditEvent
}