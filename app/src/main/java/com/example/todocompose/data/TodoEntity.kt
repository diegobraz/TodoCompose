package com.example.todocompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val title:String,
    val description:String? = null,
    val isComplete:Boolean = false
)