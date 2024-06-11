package com.madeean.todoapp.ui.screens.task

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.madeean.todoapp.data.models.TodoTask
import com.madeean.todoapp.ui.theme.topAppBarBackgroundColor
import com.madeean.todoapp.ui.theme.topAppBarContentColor
import com.madeean.todoapp.util.Action

@Composable
fun TaskAppBar(selectedTask: TodoTask?,navigateToListScreen: (Action) -> Unit) {
  if(selectedTask == null) {
    NewTaskAppBar(navigateToListScreen)
  }else{
    ExistingTaskAppBar(selectedTask, navigateToListScreen)
  }
}

@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {

  TopAppBar(
    navigationIcon = {
      BackAction(navigateToListScreen)
    },
    title = {
      Text(text = "Add Task", color = MaterialTheme.colorScheme.topAppBarContentColor)
    },
    backgroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
    actions = {
      AddAction(navigateToListScreen)
    }
  )
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
  IconButton(onClick = {
    onBackClicked(Action.NO_ACTION)
  }) {
    Icon(
      imageVector = Icons.Filled.ArrowBack,
      contentDescription = "Back Arrow",
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
  }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
  IconButton(onClick = {
    onAddClicked(Action.ADD)
  }) {
    Icon(
      imageVector = Icons.Filled.Check,
      contentDescription = "Add Task",
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
  }
}

@Composable
fun ExistingTaskAppBar(
  selectedTask: TodoTask,
  navigateToListScreen: (Action) -> Unit
) {
  TopAppBar(
    navigationIcon = {
      CloseAction(navigateToListScreen)
    },
    title = {
      Text(
        text = selectedTask.title,
        color = MaterialTheme.colorScheme.topAppBarContentColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    },
    backgroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
    actions = {
      DeleteAction(navigateToListScreen)
      UpdateAction(navigateToListScreen)
    }
  )
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
  IconButton(onClick = {
    onCloseClicked(Action.NO_ACTION)
  }) {
    Icon(
      imageVector = Icons.Filled.Close,
      contentDescription = "Close icon",
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
  }
}

@Composable
fun DeleteAction(onDeleteAction: (Action) -> Unit) {
  IconButton(onClick = {
    onDeleteAction(Action.DELETE)
  }) {
    Icon(
      imageVector = Icons.Filled.Delete,
      contentDescription = "Delete icon",
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
  }
}
@Composable
fun UpdateAction(onUpdateAction: (Action) -> Unit) {
  IconButton(onClick = {
    onUpdateAction(Action.UPDATE)
  }) {
    Icon(
      imageVector = Icons.Filled.Check,
      contentDescription = "Update icon",
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
  }
}