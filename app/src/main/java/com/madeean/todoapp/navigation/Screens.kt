package com.madeean.todoapp.navigation

import androidx.navigation.NavHostController
import com.madeean.todoapp.util.Action
import com.madeean.todoapp.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
  val list: (Action) -> Unit = {action ->
    navController.navigate("list/${action.name}") {
      popUpTo(LIST_SCREEN) { inclusive = true}
    }
  }

  val task: (Int) -> Unit = {taskid ->
    navController.navigate("task/$taskid")
  }
}