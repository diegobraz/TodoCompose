package com.example.todocompose.ui.feature

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.domain.Todo
import com.example.todocompose.domain.todo1
import com.example.todocompose.domain.todo2
import com.example.todocompose.domain.todo3
import com.example.todocompose.ui.components.TodoItem
import com.example.todocompose.ui.theme.TodoComposeTheme

@Composable
fun ListScreen() {

}

@Composable
fun ListContent(
    todos: List<Todo>
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(padding),
            contentPadding = PaddingValues(16.dp),
        ) {
            itemsIndexed(todos) {index, it ->
                TodoItem(
                    todo = it,
                    onItemClick = {},
                    onDeletedClick = {},
                    onCompleteChange = {}
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
        ListContent(todos = listOf(todo1, todo2, todo3))
    }
}