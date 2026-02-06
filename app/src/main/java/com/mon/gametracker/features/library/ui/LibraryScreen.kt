package com.mon.gametracker.features.library.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mon.gametracker.features.library.ui.components.GameCard

@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    val (isLoading, games, errorMessage) = uiState.value

    Scaffold(
        topBar = {/*Todo: componente común*/},
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )

            } else if (errorMessage != null) {
                Text(uiState.value.errorMessage!!)

            } else {
                LazyColumn {
                    items(
                        games
                    ) { game ->
                        GameCard(game = game)
                    }
                }
            }
        }
    }
}




