package com.mon.gametracker.features.game.core.domain.achievement

import com.mon.gametracker.features.game.core.domain.game.GameId
import javax.inject.Inject

class GetAchievementUseCase @Inject constructor(
    private val repository: AchievementRepository
) {
    suspend fun execute(gameId: GameId): List<Achievement> =
        repository.getAchievements(gameId = gameId)
}