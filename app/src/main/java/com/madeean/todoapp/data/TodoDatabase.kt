package com.madeean.todoapp.data

import androidx.room.Database
import com.madeean.todoapp.data.models.TodoTask

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase {
  abstract fun todoDao():TodoDao
}