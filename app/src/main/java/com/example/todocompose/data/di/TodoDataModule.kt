package com.example.todocompose.data.di

import com.example.todocompose.TodoRepositoryImpl
import com.example.todocompose.data.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class TodoDataModule {
    @Singleton
    @Binds
    abstract fun provideTodoDataSource(dataSource: TodoRepositoryImpl): TodoRepository
}