package com.mon.gametracker.features.game.domain.game

interface GameRepository {
    suspend fun getGames() : List<Game>
    suspend fun getGameById(id: String) : Game?
}