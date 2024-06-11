package com.madeean.todoapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeean.todoapp.data.models.Priority
import com.madeean.todoapp.data.models.Priority.LOW
import com.madeean.todoapp.data.models.TodoTask
import com.madeean.todoapp.data.repositories.ToDoRepository
import com.madeean.todoapp.util.Action
import com.madeean.todoapp.util.Action.ADD
import com.madeean.todoapp.util.Action.DELETE
import com.madeean.todoapp.util.Action.DELETE_ALL
import com.madeean.todoapp.util.Action.UNDO
import com.madeean.todoapp.util.Action.UPDATE
import com.madeean.todoapp.util.Constants
import com.madeean.todoapp.util.RequestState
import com.madeean.todoapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: ToDoRepository) : ViewModel() {

  val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

  val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
  val searchTextState: MutableState<String> = mutableStateOf("")

  private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
  val allTask: StateFlow<RequestState<List<TodoTask>>> = _allTasks

  fun getAllTasks() {
    _allTasks.value = RequestState.Loading
    try {
      viewModelScope.launch {
        repository.getAllTasks.collect {
          _allTasks.value = RequestState.Success(it)
        }
      }
    } catch (e: Exception) {
      _allTasks.value = RequestState.Error(e)
    }
  }

  private val _selectedTask: MutableStateFlow<TodoTask?> = MutableStateFlow(null)
  val selectedTask: StateFlow<TodoTask?> = _selectedTask

  fun getSelectedTask(taskId: Int) {
    viewModelScope.launch {
      repository.getSelectedTask(taskId).collect {
        _selectedTask.value = it
      }
    }
  }

  val id: MutableState<Int> = mutableIntStateOf(0)
  val title: MutableState<String> = mutableStateOf("")
  val description: MutableState<String> = mutableStateOf("")
  val priority: MutableState<Priority> = mutableStateOf(LOW)

  fun updateTaskField(selectedTask: TodoTask?) {
    if (selectedTask != null) {
      id.value = selectedTask.id
      title.value = selectedTask.title
      description.value = selectedTask.description
      priority.value = selectedTask.priority
    } else {
      id.value = 0
      title.value = ""
      description.value = ""
      priority.value = LOW
    }
  }

  fun updateTitle(newTitle: String) {
    if (newTitle.length < Constants.MAX_TITLE_LENGTH) {
      title.value = newTitle
    }
  }

  fun validateFields(): Boolean {
    return title.value.isNotEmpty() && description.value.isNotEmpty()
  }

  private fun addTask() {
    viewModelScope.launch(Dispatchers.IO) {
      val toDoTask = TodoTask(
        title = title.value,
        description = description.value,
        priority = priority.value
      )
      repository.addTask(toDoTask)
    }
  }

  private fun updateTask() {
    viewModelScope.launch(Dispatchers.IO) {
      val toDoTask = TodoTask(
        id = id.value,
        title = title.value,
        description = description.value,
        priority = priority.value
      )
      repository.updateTask(toDoTask)
    }
  }

  private fun deleteTask() {
    viewModelScope.launch(Dispatchers.IO) {
      val toDoTask = TodoTask(
        id = id.value,
        title = title.value,
        description = description.value,
        priority = priority.value
      )
      repository.deleteTask(toDoTask)
    }
  }

  fun handleDatabaseActions(action: Action) {
    when (action) {
      ADD -> {
        addTask()
      }

      UPDATE -> {
        updateTask()
      }

      DELETE -> {
        deleteTask()
      }

      DELETE_ALL -> {
      }

      UNDO -> {
      }

      else -> {

      }
    }
    this.action.value = Action.NO_ACTION
  }

}