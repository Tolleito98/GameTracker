package com.mon.gametracker.features.game.domain.game

import com.mon.gametracker.features.game.domain.achievement.Achievement
import kotlinx.serialization.Serializable

data class Game(
    val id: GameId,
    val name: String,
    val imageURL: String,
    val genre: String,
    val developer: String,
    val rating: Double,
    val achievements: List<Achievement>? = null
)

data class GameSummary(
    val id: GameId,
    val name: String,
    val imageURL: String,
    val rating: Double,
    val genre: String
)

@Serializable
@JvmInline
value class GameId(val value: String)