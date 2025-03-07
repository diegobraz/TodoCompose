package com.example.todocompose.data.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todocompose.data.TodoDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo-app"
        ).build()
    }

    @Provides
    fun provideUserDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

}