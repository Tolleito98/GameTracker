package com.mon.gametracker.features.game.core.data.remote

import android.util.Log
import com.mon.gametracker.features.game.core.data.mappers.toDomain
import com.mon.gametracker.features.game.core.domain.achievement.Achievement
import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.core.domain.game.GameId

class AchievementRepositoryImpl(
    private val api: GameApiService
) : AchievementRepository {

    override suspend fun getAchievements(gameId: GameId): List<Achievement> {
        return try {
            val response = api.getGameAchievements(gameId.value)
            response.results.map { it.toDomain(gameId) }


        } catch (e: Exception) {
            Log.e("AchievementRepo", "Error fetching achievements", e)
            emptyList()
        }
    }

    override suspend fun getAchievementById(
        gameId: GameId,
        achievementId: AchievementId
    ): Achievement? {
        TODO("Not yet implemented")
    }

    override suspend fun setCompleted(
        gameId: GameId,
        achievementId: AchievementId,
        isCompleted: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }
}