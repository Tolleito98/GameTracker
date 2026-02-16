package com.mon.gametracker.core.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.mon.gametracker.features.game.domain.game.GameId
import com.mon.gametracker.features.game.domain.game.GameSummary


@Composable
fun GameList(
    games: List<GameSummary>,
    onGameClick: (GameId) -> Unit,
) {
    LazyColumn {

        items(
            items = games,
            key = { it.id.value }
        ) { game ->
            GameCard(
                game = game,
                onCLick = { onGameClick(game.id) },
            )
        }
    }
}