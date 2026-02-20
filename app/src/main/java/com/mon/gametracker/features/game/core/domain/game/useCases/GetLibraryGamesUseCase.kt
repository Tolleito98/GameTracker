package com.mon.gametracker.features.game.core.domain.game.useCases

import com.mon.gametracker.features.game.core.domain.game.GameRepository
import com.mon.gametracker.features.game.core.domain.game.GameSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLibraryGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    fun execute(): Flow<List<GameSummary>> = repository.getLibraryGames()
}