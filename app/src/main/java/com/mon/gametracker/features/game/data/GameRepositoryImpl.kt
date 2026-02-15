package com.mon.gametracker.features.game.data

import com.mon.gametracker.features.game.data.mappers.toSummary
import com.mon.gametracker.features.game.data.remote.GameApiService
import com.mon.gametracker.features.game.domain.game.Game
import com.mon.gametracker.features.game.domain.game.GameId
import com.mon.gametracker.features.game.domain.game.GameRepository
import com.mon.gametracker.features.game.domain.game.GameSummary
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameApiService
) : GameRepository {

    override suspend fun getGames(): List<GameSummary> {
        return try {
            val response = api.getGames()
            response.results.map { it.toSummary() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getGameById(id: GameId): Game? {
        TODO("Not yet implemented")
    }
}