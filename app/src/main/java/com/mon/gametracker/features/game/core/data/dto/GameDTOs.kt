package com.mon.gametracker.features.game.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponseDTO(
    @SerialName(value = "results") val results: List<GameDTO>
)

@Serializable
data class GameDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("background_image") val backgroundImage: String? = null,
    @SerialName("rating") val rating: Double,
    @SerialName("genres") val genres: List<GenreDTO> = emptyList()
)

@Serializable
data class GenreDTO(
        @SerialName(value = "name") val name: String
)