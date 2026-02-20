package com.mon.gametracker.features.game.core.domain.game.useCases

import com.mon.gametracker.features.game.core.domain.game.Game
import com.mon.gametracker.features.game.core.domain.game.GameRepository
import jakarta.inject.Inject

class AddGameToLibraryUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun execute(game: Game) {
        repository.addGameToLibrary(game)
    }
}