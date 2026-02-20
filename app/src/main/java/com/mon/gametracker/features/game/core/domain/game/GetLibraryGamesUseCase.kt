package com.mon.gametracker.features.game.core.domain.game

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLibraryGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    fun execute(): Flow<List<GameSummary>> = repository.getLibraryGames()
}