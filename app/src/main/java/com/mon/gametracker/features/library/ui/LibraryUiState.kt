package com.mon.gametracker.features.library.ui

import com.mon.gametracker.features.library.domain.Game

data class LibraryUiState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val errorMessage: String? = null
)
