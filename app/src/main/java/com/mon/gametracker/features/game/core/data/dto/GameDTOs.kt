package com.mon.gametracker.features.game.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponseDTO(
    @SerialName("results") val results: List<GameDTO>
)

@Serializable
data class GameDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("background_image") val backgroundImage: String? = null,
    @SerialName("rating") val rating: Double = 0.0,
    @SerialName("genres") val genres: List<GenreDTO> = emptyList(),
    @SerialName("developers") val developers: List<DeveloperDTO> = emptyList(),

    // 👇 esto es un INT en RAWG
    @SerialName("achievements_count") val achievementsCount: Int = 0
)

@Serializable
data class GenreDTO(
    @SerialName("name") val name: String
)

@Serializable
data class DeveloperDTO(
    @SerialName("name") val name: String
)

@Serializable
data class AchievementResponseDTO(
    @SerialName("results") val results: List<AchievementDTO>
)

@Serializable
data class AchievementDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("image") val image: String? = null
)