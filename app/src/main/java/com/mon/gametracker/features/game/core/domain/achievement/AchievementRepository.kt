package com.mon.gametracker.features.game.core.domain.achievement

import com.mon.gametracker.features.game.core.domain.game.GameId

interface AchievementRepository {
    suspend fun getAchievements(
        gameId: GameId
    ) : List<Achievement>
    suspend fun getAchievementById(
        gameId: GameId,
        achievementId: AchievementId
    ) : Achievement?

    suspend fun setCompleted(
        gameId: GameId,
        achievementId: AchievementId,
        isCompleted: Boolean
    ) : Boolean

    suspend fun saveAchievements(
        gameId: GameId,
        achievements: List<Achievement>
    )


}