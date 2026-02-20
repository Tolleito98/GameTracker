package com.mon.gametracker.features.game.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
class GameEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val genre: String,
    val developer: String,
    val rating: Double,
    val isFavorite: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)