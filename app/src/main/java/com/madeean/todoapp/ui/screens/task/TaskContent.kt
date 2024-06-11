package com.madeean.todoapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.madeean.todoapp.components.PriorityDropDown
import com.madeean.todoapp.data.models.Priority
import com.madeean.todoapp.ui.theme.LARGE_PADDING
import com.madeean.todoapp.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
  title: String,
  onTitleChange: (String) -> Unit,
  description: String,
  onDescriptionChange: (String) -> Unit,
  priority: Priority,
  onPrioritySelected: (Priority) -> Unit,
  innerPaddingValues: PaddingValues
) {

  Column(
    modifier = Modifier
      .padding(innerPaddingValues)
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(all = LARGE_PADDING)
  ) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = title,
      onValueChange = { onTitleChange(it) },
      label = { Text(text = "Title") },
      textStyle = MaterialTheme.typography.bodyMedium,
      singleLine = true
    )
    Divider(
      modifier = Modifier.height(MEDIUM_PADDING),
      color = MaterialTheme.colorScheme.background
    )
    PriorityDropDown(priority, onPrioritySelected)
    OutlinedTextField(
      modifier = Modifier.fillMaxSize(),
      value = description,
      onValueChange = { onDescriptionChange(it) },
      label = { Text(text = "Description") },
      textStyle = MaterialTheme.typography.bodyMedium
    )
  }

}