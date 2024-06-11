package com.madeean.todoapp.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.madeean.todoapp.data.models.TodoTask
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import com.madeean.todoapp.util.Action

@Composable
fun TaskScreen(
  selectedTask: TodoTask?,
  sharedViewModel: SharedViewModel,
  navigateToListScreen: (Action) -> Unit
) {

  val title by sharedViewModel.title
  val description by sharedViewModel.description
  val priority by sharedViewModel.priority

  val context = LocalContext.current

  Scaffold(
    topBar = {
      TaskAppBar(selectedTask, navigateToListScreen = {
        if(it == Action.NO_ACTION) {
          navigateToListScreen(it)
        } else {
          if(sharedViewModel.validateFields()) {
            navigateToListScreen(it)
          }else{
            displayToast(context)
          }
        }
      })
    },
    content = { paddingValues ->
      TaskContent(
        title = title,
        onTitleChange = {
          sharedViewModel.updateTitle(it)
        },
        description = description,
        onDescriptionChange = {
          sharedViewModel.description.value = it
        },
        priority = priority,
        onPrioritySelected = {
          sharedViewModel.priority.value = it
        },
        innerPaddingValues = paddingValues
      )
    }

  )
}

fun displayToast(context: Context) {
  Toast.makeText(context, "Fields empty", Toast.LENGTH_SHORT).show()
}
