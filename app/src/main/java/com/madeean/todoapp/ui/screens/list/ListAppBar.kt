package com.madeean.todoapp.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.madeean.todoapp.R
import com.madeean.todoapp.components.PriorityItem
import com.madeean.todoapp.data.models.Priority
import com.madeean.todoapp.data.models.Priority.HIGH
import com.madeean.todoapp.data.models.Priority.LOW
import com.madeean.todoapp.data.models.Priority.NONE
import com.madeean.todoapp.ui.theme.LARGE_PADDING
import com.madeean.todoapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.madeean.todoapp.ui.theme.topAppBarBackgroundColor
import com.madeean.todoapp.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar() {
//  DefaultListAppBar(onSearchClicked = {}, onSortedClicked = {}, onDeleteClicked = {})
  SearchAppBar(text = "", onTextChange = {}, onCLoseClicked = {}, onSearchClicked = {})
}

@Composable
fun DefaultListAppBar(
  onSearchClicked: () -> Unit,
  onSortedClicked: (Priority) -> Unit,
  onDeleteClicked: () -> Unit
) {
  TopAppBar(
    title = {
      Text(text = "Tasks", color = MaterialTheme.colorScheme.topAppBarContentColor)
    },
    backgroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
    actions = {
      ListAppBarActions(onSearchClicked, onSortedClicked, onDeleteClicked)
    }
  )
}

@Composable
fun ListAppBarActions(
  onSearchClicked: () -> Unit,
  onSortedClicked: (Priority) -> Unit,
  onDeleteClicked: () -> Unit
) {
  SearchActions(onSearchClicked)
  SortAction(onSortedClicked)
  DeleteAllAction(onDeleteClicked)
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

@Composable
fun SortAction(onSortedClicked: (Priority) -> Unit) {

  var expanded by remember { mutableStateOf(false) }

  IconButton(onClick = {
    expanded = true
  }) {
    Icon(
      painter = painterResource(id = R.drawable.ic_filter_list),
      contentDescription = stringResource(
        R.string.sort_task
      ),
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
    DropdownMenu(expanded = expanded, onDismissRequest = {
      expanded = false
    }) {
      DropdownMenuItem(onClick = {
        expanded = false
        onSortedClicked(LOW)
      }) {
        PriorityItem(priority = LOW)
      }
      DropdownMenuItem(onClick = {
        expanded = false
        onSortedClicked(HIGH)
      }) {
        PriorityItem(priority = HIGH)
      }
      DropdownMenuItem(onClick = {
        expanded = false
        onSortedClicked(NONE)
      }) {
        PriorityItem(priority = NONE)
      }
    }
  }
}

@Composable
fun DeleteAllAction(
  onDeleteClicked: () -> Unit
) {
  var expanded by remember { mutableStateOf(false) }

  IconButton(onClick = {
    expanded = true
  }) {
    Icon(
      painter = painterResource(id = R.drawable.ic_vertical_menu),
      contentDescription = stringResource(
        R.string.sort_task
      ),
      tint = MaterialTheme.colorScheme.topAppBarContentColor
    )
    DropdownMenu(expanded = expanded, onDismissRequest = {
      expanded = false
    }) {
      DropdownMenuItem(onClick = {
        expanded = false
        onDeleteClicked()
      }) {
        Text(
          text = stringResource(R.string.delete_all),
          style = Typography().bodyMedium,
          modifier = Modifier.padding(start = LARGE_PADDING)
        )
      }
    }
  }
}

@Composable
fun SearchAppBar(
  text: String,
  onTextChange: (String) -> Unit,
  onCLoseClicked: () -> Unit,
  onSearchClicked: (String) -> Unit
) {

  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .height(TOP_APP_BAR_HEIGHT),
    elevation = AppBarDefaults.TopAppBarElevation,
    color = MaterialTheme.colorScheme.topAppBarBackgroundColor,
  ) {
    TextField(
      modifier = Modifier.fillMaxWidth(),
      value = text,
      onValueChange = {
        onTextChange(it)
      },
      placeholder = {
        Text(text = "Search", color = Color.White, modifier = Modifier.alpha(ContentAlpha.medium))
      },
      textStyle = TextStyle(
        color = MaterialTheme.colorScheme.topAppBarContentColor,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize
      ),
      singleLine = true,
      leadingIcon = {
        IconButton(modifier = Modifier.alpha(ContentAlpha.disabled),
          onClick = {}) {
          Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.topAppBarContentColor
          )
        }
      },
      trailingIcon = {
        IconButton(onClick = {
          onCLoseClicked()
        }) {
          Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close Icon",
            tint = MaterialTheme.colorScheme.topAppBarContentColor
          )
        }
      },
      keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search
      ),
      keyboardActions = KeyboardActions(
        onSearch = {
          onSearchClicked(text)
        }
      ),
      colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        cursorColor = MaterialTheme.colorScheme.topAppBarContentColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
      )
    )
  }

}