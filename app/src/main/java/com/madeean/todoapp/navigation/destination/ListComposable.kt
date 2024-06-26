package com.madeean.todoapp.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.madeean.todoapp.ui.screens.list.ListScreen
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import com.madeean.todoapp.util.Constants.LIST_ARGUMENT_KEY
import com.madeean.todoapp.util.Constants.LIST_SCREEN
import com.madeean.todoapp.util.toAction

fun NavGraphBuilder.listComposable(
  navigateToTaskScreen: (Int) -> Unit,
  sharedViewModel: SharedViewModel
){
  composable(
    route = LIST_SCREEN,
    arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
      type = NavType.StringType
    })
  ){

    val action = it.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

    LaunchedEffect(key1 = action) {
      sharedViewModel.action.value = action
    }

    ListScreen(navigateToTaskScreen, sharedViewModel)
  }
}