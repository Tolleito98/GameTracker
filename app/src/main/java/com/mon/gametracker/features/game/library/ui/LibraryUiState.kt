package com.mon.gametracker.features.game.library.ui

import com.mon.gametracker.features.game.domain.game.Game
import com.mon.gametracker.features.game.domain.game.GameSummary

data class LibraryUiState(
    val isLoading: Boolean = false,
    val games: List<GameSummary> = emptyList(),
    val errorMessage: String? = null
)
