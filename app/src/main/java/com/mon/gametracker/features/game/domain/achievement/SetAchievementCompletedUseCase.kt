package com.mon.gametracker.features.game.domain.achievement

import com.mon.gametracker.features.game.domain.game.GameId
import javax.inject.Inject

class SetAchievementCompletedUseCase @Inject constructor(
    private val repository: AchievementRepository
) {

    suspend fun execute(
        gameId: GameId,
        achievementId: AchievementId,
        isCompleted: Boolean
    ): Boolean = repository.setCompleted(
        gameId = gameId,
        achievementId = achievementId,
        isCompleted = isCompleted
    )

}