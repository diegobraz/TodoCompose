package com.example.todocompose

import com.example.todocompose.data.TodoDao
import com.example.todocompose.data.TodoEntity
import com.example.todocompose.data.TodoRepository
import com.example.todocompose.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun insert(title: String, description: String?, id: Long? ) {
        val entity = id?.let {
            dao.getById(it)?.copy(
                title = title,
                description = description
            )
        } ?: run {
            TodoEntity(
                title = title,
                description = description,
                isComplete = false
            )
        }
        dao.insert(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existingTodo = dao.getById(id) ?: return
        val updatedTodo = existingTodo.copy(isComplete = isCompleted)
        dao.insert(updatedTodo)
    }

    override suspend fun delete(id: Long) {
        val existingTodo = dao.getById(id) ?: return
        dao.delete(existingTodo)
    }

    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isComplete = entity.isComplete
                )
            }
        }
    }
    override suspend fun getBy(id: Long): Todo? {
       return dao.getById(id)?.let { entity ->
           Todo(
               id = entity.id,
               title = entity.title,
               description = entity.description,
               isComplete = entity.isComplete
           )
       }
    }
}