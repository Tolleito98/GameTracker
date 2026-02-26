package com.mon.gametracker.features.game.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mon.gametracker.features.game.core.data.local.achievement.AchievementDAO
import com.mon.gametracker.features.game.core.data.local.achievement.AchievementEntity
import com.mon.gametracker.features.game.core.data.local.game.GameDAO
import com.mon.gametracker.features.game.core.data.local.game.GameEntity

@Database(
    entities = [
        GameEntity::class,
        AchievementEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDAO
    abstract fun achievementDao(): AchievementDAO
}