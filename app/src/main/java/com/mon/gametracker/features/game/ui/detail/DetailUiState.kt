package com.mon.gametracker.features.game.ui.detail

import com.mon.gametracker.features.game.core.domain.achievement.Achievement
import com.mon.gametracker.features.game.core.domain.game.Game

data class DetailUiState(
    val game: Game? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val achievements: List<Achievement> = emptyList()
)