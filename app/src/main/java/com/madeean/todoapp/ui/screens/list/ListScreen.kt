package com.madeean.todoapp.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.madeean.todoapp.R
import com.madeean.todoapp.ui.theme.fabBackgroundColor

@Composable
fun ListScreen(
  navigateToTaskScreen: (Int) -> Unit
) {
  Scaffold(
    topBar = {
      ListAppBar()
    },
    floatingActionButton = {
      ListFab(navigateToTaskScreen = navigateToTaskScreen)
    }
  ) { paddingValues ->

  }
}

@Composable
fun ListFab(navigateToTaskScreen: (taskId:Int) -> Unit, modifier: Modifier = Modifier) {
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