package com.madeean.todoapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.madeean.todoapp.ui.screens.list.ListScreen
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import com.madeean.todoapp.util.Constants.LIST_ARGUMENT_KEY
import com.madeean.todoapp.util.Constants.LIST_SCREEN

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
    ListScreen(navigateToTaskScreen, sharedViewModel)
  }
}