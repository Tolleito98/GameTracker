package com.mon.gametracker.features.library.domain

import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
){
    suspend fun execute(): List<Game> = repository.getGames()
}