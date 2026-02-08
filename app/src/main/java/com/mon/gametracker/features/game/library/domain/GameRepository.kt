package com.mon.gametracker.features.game.library.domain

interface GameRepository {
    suspend fun getGames() : List<Game>
    suspend fun getGameById(id: String) : Game?
}