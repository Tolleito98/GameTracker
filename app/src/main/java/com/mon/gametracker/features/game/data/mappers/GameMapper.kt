package com.mon.gametracker.features.game.data.mappers

import com.mon.gametracker.features.game.data.dto.GameDTO
import com.mon.gametracker.features.game.domain.game.GameId
import com.mon.gametracker.features.game.domain.game.GameSummary

fun GameDTO.toSummary() : GameSummary {
    return GameSummary(
        id = GameId(this.toString()),
        name = this.name,
        imageURL = this.backgroundImage ?: "",
        rating = this.rating,
        genre = this.genres.joinToString(", ") { it.name  }

    )
}