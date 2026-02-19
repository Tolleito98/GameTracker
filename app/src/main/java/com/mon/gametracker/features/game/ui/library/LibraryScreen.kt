package com.mon.gametracker.features.game.ui.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mon.gametracker.core.ui.components.AppTopBar
import com.mon.gametracker.core.ui.components.ErrorCard
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.ui.components.GameList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onNavigateToDetail: (GameId) -> Unit,
    onNavigateToAdd: () -> Unit,
    viewModel: LibraryViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val (isLoading, games, errorMessage) = uiState.value

    Scaffold(
        topBar = {
            AppTopBar(
                "Library" //Todo: cambiar a string res una vez definido el titulo
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add game"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when {
                errorMessage != null -> {
                    ErrorCard(
                        message = errorMessage,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                }

                else -> GameList(
                    games = games,
                    onGameClick = { onNavigateToDetail(it) }
                )
            }
        }
    }
}

