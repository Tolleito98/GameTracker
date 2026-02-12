package com.mon.gametracker.features.game.deatil.ui

import com.mon.gametracker.features.game.domain.achievement.Achievement
import com.mon.gametracker.features.game.domain.game.Game

data class DetailUiState(
    val game: Game? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val achievements: List<Achievement> = emptyList()
)