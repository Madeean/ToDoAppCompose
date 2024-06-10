package com.madeean.todoapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeean.todoapp.data.models.TodoTask
import com.madeean.todoapp.data.repositories.ToDoRepository
import com.madeean.todoapp.util.RequestState
import com.madeean.todoapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor (private val repository: ToDoRepository): ViewModel() {

  val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
  val searchTextState: MutableState<String> = mutableStateOf("")

  private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
  val allTask: StateFlow<RequestState<List<TodoTask>>> = _allTasks

  fun getAllTasks(){
    _allTasks.value = RequestState.Loading
    try {
      viewModelScope.launch {
        repository.getAllTasks.collect{
          _allTasks.value = RequestState.Success(it)
        }
      }
    }catch (e: Exception) {
      _allTasks.value = RequestState.Error(e)
    }
  }

}