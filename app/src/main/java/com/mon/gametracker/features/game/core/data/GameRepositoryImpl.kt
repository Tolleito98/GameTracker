package com.mon.gametracker.features.game.core.data

import com.mon.gametracker.features.game.core.data.local.GameDAO
import com.mon.gametracker.features.game.core.data.mappers.toEntity
import com.mon.gametracker.features.game.core.data.mappers.toGame
import com.mon.gametracker.features.game.core.data.mappers.toSummary
import com.mon.gametracker.features.game.core.data.remote.GameApiService
import com.mon.gametracker.features.game.core.domain.game.Game
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GameRepository
import com.mon.gametracker.features.game.core.domain.game.GameSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameApiService,
    private val dao: GameDAO
) : GameRepository {

    override suspend fun getApiGames(query: String?): List<GameSummary> {
        return try {
            val response = api.getGames(query = query)
            response.results.map { it.toSummary() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getLibraryGames(): Flow<List<GameSummary>> {
        return dao.getAllGames().map { entities ->
            entities.map { it.toSummary() }
        }
    }

    override suspend fun getGameById(id: GameId): Game? {
        return try {
            api.getGame(id.value).toGame()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addGameToLibrary(game: Game) {
        dao.insertGame(game.toEntity())
    }

    suspend fun saveGame(game: Game) {
        dao.insertGame(game.toEntity())
    }
}