package com.mon.gametracker.features.game.core.domain.game

import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getApiGames(query: String? = null) : List<GameSummary>
    fun getLibraryGames(): Flow<List<GameSummary>>
    suspend fun getGameById(id: GameId) : Game?
    suspend fun addGameToLibrary(game: Game)
}