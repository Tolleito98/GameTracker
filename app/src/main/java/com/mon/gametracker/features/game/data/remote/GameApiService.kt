package com.mon.gametracker.features.game.data.remote

import com.mon.gametracker.features.game.data.dto.GameResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("games")
    suspend fun getGames(
        @Query("page_size") pageSize: Int = 10,
        @Query("search") query: String? = null
    ): GameResponseDTO
}