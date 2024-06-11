package com.madeean.todoapp.ui.screens.list

import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.madeean.todoapp.R
import com.madeean.todoapp.ui.theme.fabBackgroundColor
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import com.madeean.todoapp.util.Action
import com.madeean.todoapp.util.Action.NO_ACTION
import com.madeean.todoapp.util.SearchAppBarState
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
  navigateToTaskScreen: (Int) -> Unit,
  sharedViewModel: SharedViewModel
) {
  LaunchedEffect(key1 = true) {
    sharedViewModel.getAllTasks()
  }
  val action by sharedViewModel.action
  val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
  val searchTextState: String by sharedViewModel.searchTextState
  val allTasks by sharedViewModel.allTask.collectAsState()
  val scaffoldState = rememberScaffoldState()
  
  DisplaySnackBar(
    scaffoldState = scaffoldState,
    handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action) },
    taskTitle = sharedViewModel.title.value,
    action = action
  )

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      ListAppBar(
        sharedViewModel,
        searchAppBarState,
        searchTextState
      )
    },
    floatingActionButton = {
      ListFab(navigateToTaskScreen = navigateToTaskScreen)
    },
    content = { padding ->
      ListContent(
        innerPadding = padding,
        toDoTasks = allTasks,
        navigateToTaskScreen = navigateToTaskScreen
      )
    }

  )
}

@Composable
fun ListFab(navigateToTaskScreen: (taskId: Int) -> Unit, modifier: Modifier = Modifier) {
  FloatingActionButton(
    onClick = {
      navigateToTaskScreen(-1)
    },
    containerColor = MaterialTheme.colorScheme.fabBackgroundColor
  ) {
    Icon(
      imageVector = Icons.Filled.Add,
      contentDescription = stringResource(R.string.icon_add_button),
      tint = Color.White
    )
  }
}

@Composable
fun DisplaySnackBar(
  scaffoldState: ScaffoldState,
  handleDatabaseActions: () -> Unit,
  taskTitle: String,
  action: Action
) {

  handleDatabaseActions()

  val scope = rememberCoroutineScope()
  LaunchedEffect(key1 = action) {
    if (action != NO_ACTION) {
      scope.launch {
        val snackbar = scaffoldState.snackbarHostState.showSnackbar(
          message = "${action.name}: $taskTitle",
          actionLabel = "OK"
        )
      }
    }
  }
}