package com.mon.gametracker.features.game.core.data.mappers

import com.mon.gametracker.features.game.core.data.dto.GameDTO
import com.mon.gametracker.features.game.core.domain.game.Game
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GameSummary

fun GameDTO.toSummary(): GameSummary {
    return GameSummary(
        id = GameId(this.toString()),
        name = this.name,
        imageURL = this.backgroundImage ?: "",
        rating = this.rating,
        genre = this.genres.joinToString(", ") { it.name }
    )
}

fun GameDTO.toGame(): Game {
    return Game(
        id = GameId(id.toString()),
        name = name,
        imageURL = backgroundImage ?: "",
        genre = genres.joinToString(", ") { it.name },
        rating = rating,
        achievements = emptyList(),
        developer = developers.joinToString(", ") { it.name }
    )
}
