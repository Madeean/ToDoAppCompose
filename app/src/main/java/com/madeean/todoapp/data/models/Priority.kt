package com.madeean.todoapp.data.models

import androidx.compose.ui.graphics.Color
import com.madeean.todoapp.ui.theme.HighPriorityColor
import com.madeean.todoapp.ui.theme.LowPriorityColor
import com.madeean.todoapp.ui.theme.MediumPriorityColor
import com.madeean.todoapp.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
  HIGH(HighPriorityColor),
  MEDIUM(MediumPriorityColor),
  LOW(LowPriorityColor),
  NONE(NonePriorityColor)
}