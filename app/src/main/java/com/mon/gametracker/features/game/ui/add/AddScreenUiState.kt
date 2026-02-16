package com.mon.gametracker.features.game.ui.add

import com.mon.gametracker.features.game.core.domain.game.GameSummary

data class AddScreenUiState (
    val searchQuery: String = "",
    val games: List<GameSummary> = emptyList(),
    val errorMesage: String? = null,
    val isLoading: Boolean = false,
    val suggestions: List<GameSummary> = emptyList(),
    val isSearchingSuggestions: Boolean = false
)