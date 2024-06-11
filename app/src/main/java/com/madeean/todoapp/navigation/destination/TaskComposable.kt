package com.madeean.todoapp.navigation.destination

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.madeean.todoapp.ui.screens.task.TaskScreen
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import com.madeean.todoapp.util.Action
import com.madeean.todoapp.util.Constants
import com.madeean.todoapp.util.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
  sharedViewModel: SharedViewModel,
  navigateToListScreen: (Action) -> Unit
) {
  composable(
    route = Constants.TASK_SCREEN,
    arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
      type = NavType.IntType
    })
  ) {
    val taskId = it.arguments?.getInt(TASK_ARGUMENT_KEY) ?: 0
    sharedViewModel.getSelectedTask(taskId)
    val selectedTask by sharedViewModel.selectedTask.collectAsState()

    TaskScreen(selectedTask ,navigateToListScreen)
  }
}