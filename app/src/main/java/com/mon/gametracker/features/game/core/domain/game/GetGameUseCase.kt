package com.mon.gametracker.features.game.core.domain.game

import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(id: GameId): Game? = repository.getGameById(id)
}