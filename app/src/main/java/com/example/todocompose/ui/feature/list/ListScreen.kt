package com.example.todocompose.ui.feature.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todocompose.TodoRepositoryImpl
import com.example.todocompose.data.TodoDatabaseProvider
import com.example.todocompose.domain.Todo
import com.example.todocompose.domain.todo1
import com.example.todocompose.domain.todo2
import com.example.todocompose.domain.todo3
import com.example.todocompose.navigation.AddEditRoute
import com.example.todocompose.ui.UiEvent
import com.example.todocompose.ui.components.TodoItem
import com.example.todocompose.ui.feature.addEdit.AddEditViewModel
import com.example.todocompose.ui.theme.TodoComposeTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit
) {

    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context).todoDao
    val viewModel = viewModel<ListViewModel> {
        ListViewModel(
            repository = TodoRepositoryImpl(
                dao = database
            )
        )
    }

    val todos by viewModel.todos.collectAsState()
    LaunchedEffect(Unit){
        viewModel.uiEvent.collect{uiEvent ->
          when(uiEvent){
              is UiEvent.Navigate<*> -> {
                  when(uiEvent.route){
                      is AddEditRoute -> {
                          navigateToAddEditScreen(uiEvent.route.id)
                      }
                  }
              }
              UiEvent.NavigateBack -> {

              }
              is UiEvent.ShowSnackBar -> {

              }
          }
        }
    }

    ListContent(todos = todos, viewModel::onEvent)
}

@Composable
fun ListContent(
    todos: List<Todo>,
    onEvent: (ListEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ListEvent.AddEdit(null))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(padding),
            contentPadding = PaddingValues(16.dp),
        ) {
            itemsIndexed(todos) { index, todo ->
                TodoItem(
                    todo = todo,
                    onItemClick = {
                        onEvent(ListEvent.AddEdit(todo.id))
                    },
                    onDeletedClick = {
                        onEvent(ListEvent.Delete(todo.id))
                    },
                    onCompleteChange = {
                        onEvent(ListEvent.Complete(todo.id, it))
                    }
                )
                if (index < todos.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun ListContentPreview() {
    TodoComposeTheme {
        ListContent(todos = listOf(todo1, todo2, todo3), onEvent = {})
    }
}