package com.mon.gametracker.features.game.domain.game

import com.mon.gametracker.features.game.domain.achievement.Achievement

data class Game(
    val id: GameId,
    val name: String,
    val imageURL: String,
    val genre: String,
    val developer: String,
    val rating: Double,
    val achievements: List<Achievement>? = null
)

@JvmInline
value class GameId(val value: String)