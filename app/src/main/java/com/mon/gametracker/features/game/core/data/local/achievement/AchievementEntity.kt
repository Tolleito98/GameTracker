package com.mon.gametracker.features.game.core.data.local.achievement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String,
    val gameId: String,
    val name: String,
    val description: String,
    val isCompleted: Boolean,
    val completionDate: String? = null
)