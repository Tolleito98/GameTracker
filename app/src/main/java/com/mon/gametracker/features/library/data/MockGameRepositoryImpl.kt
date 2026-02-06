package com.mon.gametracker.features.library.data

import com.mon.gametracker.features.library.domain.Game
import com.mon.gametracker.features.library.domain.GameRepository
import javax.inject.Inject

class MockGameRepositoryImpl @Inject constructor(): GameRepository {

    private val games = listOf(
        Game("1", "Zelda", "https://example.com/zelda.png", "Adventure", "Nintendo", 9.5),
        Game("2", "Mario Kart", "https://example.com/mariokart.png", "Racing", "Nintendo", 8.7),
        Game("3", "Hollow Knight", "https://example.com/hollowknight.png", "Metroidvania", "Team Cherry", 9.0)
    )

    override suspend fun getGames(): List<Game> = games
    override suspend fun getGameById(id: String): Game? = games.find { it.id == id }
}