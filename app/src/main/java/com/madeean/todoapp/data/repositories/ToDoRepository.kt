package com.madeean.todoapp.data.repositories

import com.madeean.todoapp.data.TodoDao
import com.madeean.todoapp.data.models.TodoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val todoDao: TodoDao) {

  val getAllTasks: Flow<List<TodoTask>> = todoDao.getAllTasks()
  val sortByLowPriority: Flow<List<TodoTask>> = todoDao.sortByLowPriority()
  val sortByHighPriority: Flow<List<TodoTask>> = todoDao.sortByHighPriority()

  fun getSelectedTask(taskId: Int): Flow<TodoTask> {
    return todoDao.getSelectedTask(taskId = taskId)
  }

  suspend fun addTask(todoTask: TodoTask) {
    todoDao.addTask(todoTask)
  }
  suspend fun updateTask(todoTask: TodoTask) {
    todoDao.updateTask(todoTask)
  }
  suspend fun deleteTask(todoTask: TodoTask) {
    todoDao.deleteTask(todoTask)
  }

  suspend fun deleteAllTask() {
    todoDao.deleteAllTask()
  }

  fun searchDatabase(searchQuery: String) : Flow<List<TodoTask>> {
    return todoDao.searchDatabase(searchQuery)
  }

}