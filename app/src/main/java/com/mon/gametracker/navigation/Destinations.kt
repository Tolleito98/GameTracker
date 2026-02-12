package com.mon.gametracker.navigation

import com.mon.gametracker.features.game.domain.game.GameId
import kotlinx.serialization.Serializable

@Serializable
object LibraryDestination

@Serializable
data class GameDetailDestination(val gameId: String)