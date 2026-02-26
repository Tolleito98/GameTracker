package com.mon.gametracker.features.game.core.data

import android.util.Log
import com.mon.gametracker.features.game.core.data.local.achievement.AchievementDAO
import com.mon.gametracker.features.game.core.data.remote.GameApiService
import com.mon.gametracker.features.game.core.data.mappers.toDomain
import com.mon.gametracker.features.game.core.data.mappers.toEntity
import com.mon.gametracker.features.game.core.domain.achievement.Achievement
import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.core.domain.game.GameId
import javax.inject.Inject

class AchievementRepositoryImpl @Inject constructor(
    private val api: GameApiService,
    private val dao: AchievementDAO
) : AchievementRepository {

    override suspend fun getAchievements(gameId: GameId): List<Achievement> {
        val localEntities = dao.getAchievementsByGameId(gameId.value)

        return if (localEntities.isNotEmpty()) {
            localEntities.map { it.toDomain() }
        } else {
            try {
                val response = api.getGameAchievements(gameId.value)
                val domainAchievements = response.results.map { it.toDomain(gameId) }

                saveAchievements(gameId, domainAchievements)

                domainAchievements
            } catch (e: Exception) {
                Log.e("AchievementRepo", "Error fetching achievements", e)
                emptyList()
            }
        }
    }

    override suspend fun setCompleted(
        gameId: GameId,
        achievementId: AchievementId,
        isCompleted: Boolean
    ): Boolean {
        return try {
            val entity = dao.getAchievementById(achievementId.value)
            if (entity != null) {
                val updatedEntity = entity.copy(
                    isCompleted = isCompleted,
                    completionDate = if (isCompleted) java.time.LocalDate.now().toString() else null
                )
                dao.updateAchievement(updatedEntity)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("AchievementRepo", "Error updating status", e)
            false
        }
    }

    override suspend fun saveAchievements(
        gameId: GameId,
        achievements: List<Achievement>
    ) {
        try {
            val entities = achievements.map { it.toEntity(gameId) }
            dao.insertAchievements(entities)
        } catch (e: Exception) {
            Log.e("AchievementRepo", "Error saving achievements", e)
        }
    }

    override suspend fun getAchievementById(
        gameId: GameId,
        achievementId: AchievementId
    ): Achievement? {
        return dao.getAchievementById(achievementId.value)?.toDomain()
    }
}