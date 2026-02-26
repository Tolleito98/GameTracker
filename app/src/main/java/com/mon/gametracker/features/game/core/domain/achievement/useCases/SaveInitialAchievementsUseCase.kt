package com.mon.gametracker.features.game.core.domain.achievement

import com.mon.gametracker.features.game.core.domain.game.GameId
import javax.inject.Inject

class SaveInitialAchievementsUseCase @Inject constructor(
    private val repository: AchievementRepository
) {
    suspend fun execute(gameId: GameId, achievements: List<Achievement>) {
        repository.saveAchievements(gameId, achievements)
    }
}