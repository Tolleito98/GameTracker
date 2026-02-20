package com.mon.gametracker.features.game.core.data.remote

import com.mon.gametracker.features.game.core.data.dto.AchievementResponseDTO
import com.mon.gametracker.features.game.core.data.dto.GameDTO
import com.mon.gametracker.features.game.core.data.dto.GameResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApiService {
    @GET("games")
    suspend fun getGames(
        @Query("page_size") pageSize: Int = 10,
        @Query("search") query: String? = null
    ): GameResponseDTO


    @GET(value = "games/{id}")
    suspend fun getGame(
        @Path(value = "id") id: String
    ): GameDTO


    @GET("games/{id}/achievements")
    suspend fun getGameAchievements(
        @Path("id") id: String,
        @Query("page_size") pageSize: Int = 20
    ): AchievementResponseDTO
}