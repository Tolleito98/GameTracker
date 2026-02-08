package com.mon.gametracker.features.game.library.domain

data class Game(
    val id: String,
    val name: String,
    val imageURL: String,
    val genre: String,
    val developer: String,
    val rating: Double,
    val achievements: List<String>? = null /*Todo: implement achievement list*/
)