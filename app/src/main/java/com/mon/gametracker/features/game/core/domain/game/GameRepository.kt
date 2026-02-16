package com.mon.gametracker.features.game.core.domain.game

interface GameRepository {
    suspend fun getGames() : List<GameSummary>
    suspend fun getGameById(id: GameId) : Game?
}