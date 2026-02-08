package com.mon.gametracker.features.game.library.ui

import com.mon.gametracker.features.game.domain.game.Game

data class LibraryUiState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val errorMessage: String? = null
)
