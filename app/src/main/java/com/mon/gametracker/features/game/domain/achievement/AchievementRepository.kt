package com.mon.gametracker.features.game.domain.achievement

import com.mon.gametracker.features.game.domain.game.GameId

interface AchievementRepository {
    suspend fun getAchievements(gameId: GameId) : List<Achievement>
    suspend fun getAchievementById(gameId: GameId, achievementId: AchievementId)
}