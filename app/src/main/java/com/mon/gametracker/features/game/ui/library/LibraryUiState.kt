package com.mon.gametracker.features.game.ui.library

import com.mon.gametracker.features.game.core.domain.game.GameSummary

data class LibraryUiState(
    val isLoading: Boolean = false,
    val games: List<GameSummary> = emptyList(),
    val errorMessage: String? = null
)
