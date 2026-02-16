package com.mon.gametracker.features.game.core.domain.game

import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
){
    suspend fun execute(): List<GameSummary> = repository.getGames()
}