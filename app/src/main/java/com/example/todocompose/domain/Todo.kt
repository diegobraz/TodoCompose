package com.example.todocompose.domain

data class Todo(
    val id: Long,
    val title:String,
    val description:String? = null,
    val isComplete:Boolean = false
)

// fake objects
val todo1 = Todo(1, "Todo 1", "Description 1", false)
val todo2 = Todo(2, "Todo 2", "Description 2", true)
val todo3 = Todo(3, "Todo 3", "Description 3", false)