package com.mon.gametracker.features.game.core.domain.game.useCases

import com.mon.gametracker.features.game.core.domain.game.GameRepository
import com.mon.gametracker.features.game.core.domain.game.GameSummary
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(query: String? = null): List<GameSummary> =
        repository.getApiGames(query = query)
}