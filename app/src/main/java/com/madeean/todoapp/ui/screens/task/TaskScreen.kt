package com.madeean.todoapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.madeean.todoapp.data.models.TodoTask
import com.madeean.todoapp.util.Action

@Composable
fun TaskScreen(
  selectedTask: TodoTask?,
  navigateToListScreen: (Action) -> Unit) {
  Scaffold(
    topBar = {
      TaskAppBar(selectedTask,navigateToListScreen)
    },
    content = {paddingValues ->

    }

  )
}