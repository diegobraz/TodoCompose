package com.example.todocompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocompose.data.TodoDao
import com.example.todocompose.data.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}