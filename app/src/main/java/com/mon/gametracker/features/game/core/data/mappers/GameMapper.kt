package com.mon.gametracker.features.game.core.data.mappers

import com.mon.gametracker.features.game.core.data.dto.GameDTO
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GameSummary

fun GameDTO.toSummary() : GameSummary {
    return GameSummary(
        id = GameId(this.toString()),
        name = this.name,
        imageURL = this.backgroundImage ?: "",
        rating = this.rating,
        genre = this.genres.joinToString(", ") { it.name  }

    )
}