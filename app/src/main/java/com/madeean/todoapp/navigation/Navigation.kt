package com.madeean.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.madeean.todoapp.navigation.destination.listComposable
import com.madeean.todoapp.navigation.destination.taskComposable
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import com.madeean.todoapp.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(
  navController: NavHostController,
  sharedViewmodel: SharedViewModel
) {
   val screen = remember(navController) {
     Screens(navController = navController)
   }

  NavHost(
    navController = navController,
    startDestination = LIST_SCREEN
  ) {
    listComposable(
      navigateToTaskScreen = screen.task,
      sharedViewModel = sharedViewmodel
    )
    taskComposable(
      navigateToListScreen = screen.list
    )
  }
}