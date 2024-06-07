package com.madeean.todoapp.ui.screens.list

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.madeean.todoapp.R
import com.madeean.todoapp.ui.theme.topAppBarBackgroundColor
import com.madeean.todoapp.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar() {
  DefaultListAppBar(onSearchClicked = {})
}

@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit) {
  TopAppBar(
    title = {
      Text(text = "Tasks", color = MaterialTheme.colorScheme.topAppBarContentColor)
    },
    backgroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
    actions = {
      ListAppBarActions(onSearchClicked)
    }
  )
}

@Composable
fun ListAppBarActions(onSearchClicked: () -> Unit) {
  SearchActions(onSearchClicked)
}

@Composable
fun SearchActions(onSearchClicked: () -> Unit) {
  IconButton(onClick = { onSearchClicked() }) {
    Icon(
      imageVector = Icons.Filled.Search,
      contentDescription = stringResource(R.string.search_tasks),
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
  }
}