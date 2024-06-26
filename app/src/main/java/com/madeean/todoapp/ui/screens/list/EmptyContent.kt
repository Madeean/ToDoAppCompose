package com.madeean.todoapp.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madeean.todoapp.R
import com.madeean.todoapp.ui.theme.MediumGray

@Composable
fun EmptyContent() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(
      modifier = Modifier.size(120.dp),
      painter = painterResource(id = R.drawable.ic_sad),
      contentDescription = "empty content",
      tint = MediumGray
    )
    Text(
      text = "No task found",
      color = MediumGray,
      fontWeight = FontWeight.Bold,
      fontSize = MaterialTheme.typography.displaySmall.fontSize
    )
  }
}

@Preview
@Composable
fun EmptyContentPreview(modifier: Modifier = Modifier) {
  EmptyContent()
}