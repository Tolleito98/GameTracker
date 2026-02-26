package com.mon.gametracker.features.game.core.domain.game.useCases

import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GameRepository
import jakarta.inject.Inject

class DeleteGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(gameId: GameId) {
        repository.deleteGame(gameId)
    }
}