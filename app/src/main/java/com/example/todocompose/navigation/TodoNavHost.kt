package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todocompose.ui.feature.addEdit.AddEditScreen
import com.example.todocompose.ui.feature.addEdit.AddEditViewModel
import com.example.todocompose.ui.feature.list.ListScreen
import com.example.todocompose.ui.feature.list.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id:Long? = null)


@Composable
fun TodoNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ListRoute) {
        composable<ListRoute> {
            val viewModelList: ListViewModel = hiltViewModel()
            ListScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id))
                },
                viewModel = viewModelList
            )
        }

        composable<AddEditRoute> { backStackEntry ->
            val addEditRoute = backStackEntry.toRoute<AddEditRoute>()
            val viewModelAddEdit: AddEditViewModel = hiltViewModel()

            AddEditScreen(
                addEditRoute.id,
                navigateBack = {
                    navController.popBackStack()
                },
                viewModel = viewModelAddEdit

            )
        }
    }
}