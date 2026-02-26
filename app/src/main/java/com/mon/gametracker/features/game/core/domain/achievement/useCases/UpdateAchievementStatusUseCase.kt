package com.mon.gametracker.features.game.core.domain.achievement.useCases

import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.core.domain.game.GameId
import javax.inject.Inject

class UpdateAchievementStatusUseCase @Inject constructor(
    private val repository: AchievementRepository
) {
    suspend fun execute(
        gameId: GameId,
        achievementId: AchievementId,
        isCompleted: Boolean
    ): Boolean {
        return repository.setCompleted(
            gameId = gameId,
            achievementId = achievementId,
            isCompleted = isCompleted
        )
    }
}