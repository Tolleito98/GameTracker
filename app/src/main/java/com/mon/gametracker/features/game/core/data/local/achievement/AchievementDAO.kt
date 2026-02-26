package com.mon.gametracker.features.game.core.data.local.achievement

import androidx.room.*

@Dao
interface AchievementDAO {

    @Query("SELECT * FROM achievements WHERE gameId = :gameId")
    suspend fun getAchievementsByGameId(gameId: String): List<AchievementEntity>

    @Query("SELECT * FROM achievements WHERE id = :id")
    suspend fun getAchievementById(id: String): AchievementEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)

    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)
}