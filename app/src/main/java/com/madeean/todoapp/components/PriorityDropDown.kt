package com.madeean.todoapp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.madeean.todoapp.data.models.Priority
import com.madeean.todoapp.data.models.Priority.HIGH
import com.madeean.todoapp.data.models.Priority.LOW
import com.madeean.todoapp.data.models.Priority.MEDIUM
import com.madeean.todoapp.ui.theme.PRIORITY_DROPDOWN_HEIGHT
import com.madeean.todoapp.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
  priority: Priority,
  onPrioritySelected: (Priority) -> Unit
) {
  var expanded by remember {
    mutableStateOf(false)
  }

  val angle: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(PRIORITY_DROPDOWN_HEIGHT)
      .clickable { expanded = true }
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
      ),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Canvas(
      modifier = Modifier
        .size(PRIORITY_INDICATOR_SIZE)
        .weight(1f)
    ) {
      drawCircle(color = priority.color)
    }
    Text(
      modifier = Modifier.weight(8f),
      text = priority.name,
      style = MaterialTheme.typography.displayMedium
    )
    IconButton(
      modifier = Modifier
        .alpha(ContentAlpha.medium)
        .rotate(degrees = angle)
        .weight(1.5f),
      onClick = { expanded = true }
    ) {
      Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
    }

    DropdownMenu(modifier = Modifier.fillMaxWidth(),expanded = expanded, onDismissRequest = { expanded = false }) {
      DropdownMenuItem(onClick = {
        expanded = false
        onPrioritySelected(LOW)
      }) {
        PriorityItem(priority = LOW)
      }
      DropdownMenuItem(onClick = {
        expanded = false
        onPrioritySelected(MEDIUM)
      }) {
        PriorityItem(priority = MEDIUM)
      }
      DropdownMenuItem(onClick = {
        expanded = false
        onPrioritySelected(HIGH)
      }) {
        PriorityItem(priority = HIGH)
      }
    }
  }
}