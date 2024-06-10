package com.madeean.todoapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.madeean.todoapp.data.models.Priority
import com.madeean.todoapp.ui.theme.LARGE_PADDING
import com.madeean.todoapp.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(priority: Priority) {

  Row(
    verticalAlignment = Alignment.CenterVertically
  ) {
    Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
      drawCircle(color = priority.color)
    }
    Text(
      modifier = Modifier.padding(start = LARGE_PADDING),
      text = priority.name,
      style = Typography().bodyMedium,
      color = MaterialTheme.colorScheme.onSurface
    )
  }

}