package com.mon.gametracker.navigation

import kotlinx.serialization.Serializable

@Serializable
object LibraryDestination

@Serializable
object AddDestination

@Serializable
data class GameDetailDestination(val gameId: String)