package com.mon.gametracker.temp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun TestScreen(
    viewModel : TempVM = hiltViewModel()
) {
    Text(text = viewModel.message())
}