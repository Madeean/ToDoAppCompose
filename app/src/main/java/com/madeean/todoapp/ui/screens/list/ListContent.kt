package com.madeean.todoapp.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.madeean.todoapp.data.models.TodoTask
import com.madeean.todoapp.ui.theme.LARGE_PADDING
import com.madeean.todoapp.ui.theme.PRIORITY_INDICATOR_SIZE
import com.madeean.todoapp.ui.theme.TASK_ITEM_ELEVATION
import com.madeean.todoapp.ui.theme.taskItemBackgroundColor
import com.madeean.todoapp.ui.theme.taskItemTextColor
import com.madeean.todoapp.util.RequestState

@Composable
fun ListContent(
  innerPadding: PaddingValues,
  toDoTasks: RequestState<List<TodoTask>>,
  navigateToTaskScreen: (taskId: Int) -> Unit
) {
  when (toDoTasks) {
    is RequestState.Success -> {
      if (toDoTasks.data.isEmpty()) {
        EmptyContent()
      } else {
        DisplayTasks(
          innerPadding = innerPadding,
          toDoTasks = toDoTasks.data,
          navigateToTaskScreen = navigateToTaskScreen
        )
      }
    }

    else -> {

    }
  }

}

@Composable
fun DisplayTasks(
  innerPadding: PaddingValues,
  toDoTasks: List<TodoTask>,
  navigateToTaskScreen: (taskId: Int) -> Unit
) {
  LazyColumn(modifier = Modifier.padding(innerPadding)) {
    items(items = toDoTasks, key = { task ->
      task.id
    }) { task ->
      TaskItem(toDoTask = task, navigateToTaskScreen = navigateToTaskScreen)
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
  toDoTask: TodoTask,
  navigateToTaskScreen: (taskId: Int) -> Unit
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    color = MaterialTheme.colorScheme.taskItemBackgroundColor,
    shape = RectangleShape,
    elevation = TASK_ITEM_ELEVATION,
    onClick = {
      navigateToTaskScreen(toDoTask.id)
    }
  ) {
    Column(
      modifier = Modifier
        .padding(all = LARGE_PADDING)
        .fillMaxWidth()
    ) {
      Row {
        Text(
          modifier = Modifier.weight(8f),
          text = toDoTask.title,
          color = MaterialTheme.colorScheme.taskItemTextColor,
          style = MaterialTheme.typography.headlineSmall,
          fontWeight = FontWeight.Bold,
          maxLines = 1
        )
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
          contentAlignment = Alignment.TopEnd
        ) {
          Canvas(
            modifier = Modifier
              .size(PRIORITY_INDICATOR_SIZE)
          ) {
            drawCircle(
              color = toDoTask.priority.color
            )
          }
        }
      }
      Text(
        text = toDoTask.description,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.taskItemTextColor,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}