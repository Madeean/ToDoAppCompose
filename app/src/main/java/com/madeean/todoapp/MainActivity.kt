package com.madeean.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madeean.todoapp.navigation.SetupNavigation
import com.madeean.todoapp.ui.theme.ToDoAppTheme
import com.madeean.todoapp.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private lateinit var navController: NavHostController
  private val sharedViewModel: SharedViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ToDoAppTheme {
        navController = rememberNavController()
        SetupNavigation(navController = navController, sharedViewmodel = sharedViewModel)
      }
    }
  }
}