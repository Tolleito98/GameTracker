package com.mon.gametracker.features.game.core.data.mappers

import com.mon.gametracker.features.game.core.data.dto.GameDTO
import com.mon.gametracker.features.game.core.data.local.GameEntity
import com.mon.gametracker.features.game.core.domain.game.Game
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GameSummary

//##################API##################//
fun GameDTO.toSummary(): GameSummary {
    return GameSummary(
        id = GameId(this.id.toString()),
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

//##################ROOM##################//
fun Game.toEntity(): GameEntity {
    return GameEntity(
        id = this.id.value,
        name = this.name,
        imageUrl = this.imageURL,
        genre = this.genre,
        developer = this.developer,
        rating = this.rating
    )
}

fun GameEntity.toDomain(): Game {
    return Game(
        id = GameId(this.id),
        name = this.name,
        imageURL = this.imageUrl,
        genre = this.genre,
        developer = this.developer,
        rating = this.rating,
        achievements = emptyList()
    )
}

