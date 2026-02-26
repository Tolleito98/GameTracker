package com.mon.gametracker.features.game.core.domain.game.useCases

import com.mon.gametracker.features.game.core.domain.game.Game
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GameRepository
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(id: GameId): Game? = repository.getGameById(id)
}