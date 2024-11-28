package com.example.todocompose.ui.feature.addEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todocompose.TodoRepositoryImpl
import com.example.todocompose.data.TodoDatabaseProvider
import com.example.todocompose.ui.UiEvent
import com.example.todocompose.ui.theme.TodoComposeTheme

@Composable
fun AddEditScreen(
    id: Long?,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context).todoDao
    val viewModel = viewModel<AddEditViewModel> {
        AddEditViewModel(
            todoId = id,
            repository = TodoRepositoryImpl(
                dao = database
            )
        )
    }

    val title = viewModel.title
    val description = viewModel.description
    val snackbarHostState = remember{
        SnackbarHostState()
    }

    AddEditContent(title, description, onEvent = viewModel::onEvent, snackbarHostState)
    LaunchedEffect(Unit){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(message = event.message )
                }
                is UiEvent.NavigateBack -> {
                    navigateBack()
                }
                is UiEvent.Navigate<*> ->{

                }
            }

        }
    }
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    onEvent: (AddEditEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(AddEditEvent.Save) }) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValue)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = {
                    onEvent(AddEditEvent.TitleChanged(it))
                },
                label = { Text("Title") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description ?: "",
                onValueChange = { value ->
                    onEvent(AddEditEvent.DescriptionChanged(value))
                },
                label = { Text("Description (optional)") }
            )

        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    TodoComposeTheme {
        AddEditContent("teste", "", onEvent = {}, SnackbarHostState())
    }
}
