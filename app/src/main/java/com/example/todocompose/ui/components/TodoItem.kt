package com.example.todocompose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.domain.Todo
import com.example.todocompose.domain.todo1
import com.example.todocompose.domain.todo2
import com.example.todocompose.ui.theme.TodoComposeTheme

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    onCompleteChange: (Boolean) -> Unit,
    onItemClick: () -> Unit,
    onDeletedClick: () -> Unit
) {
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = todo.isComplete, onCheckedChange = onCompleteChange)

            Spacer(modifier.width(8.dp))

            Column(
                modifier = modifier.weight(1f)
            ) {
                Text(text = todo.title, style = MaterialTheme.typography.titleLarge)
                todo.description?.let {
                    Spacer(modifier.height(8.dp))
                    Text(todo.description, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier.width(8.dp))

            IconButton(onClick = onDeletedClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
private fun TodoItemPreview() {
    TodoComposeTheme {
        TodoItem(
            todo = todo1,
            onItemClick = {},
            onDeletedClick = {},
            onCompleteChange = {}
        )
    }
}


@Preview
@Composable
private fun TodoItemCompletedePreview() {
    TodoComposeTheme {
        TodoItem(
            todo = todo2,
            onItemClick = {},
            onDeletedClick = {},
            onCompleteChange = {})
    }
}