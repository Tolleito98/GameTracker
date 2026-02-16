package com.mon.gametracker.navigation

import kotlinx.serialization.Serializable

@Serializable
object LibraryDestination

@Serializable
data class GameDetailDestination(val gameId: String)