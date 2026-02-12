package com.mon.gametracker.features.game.library.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mon.gametracker.features.game.domain.game.Game
import com.mon.gametracker.features.game.domain.game.GameId
import com.mon.gametracker.features.game.domain.game.GameSummary


@Composable
fun GameCard(
    game: GameSummary,
    onCLick: (GameId) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable(
                enabled = true,
                onClick = {
                    onCLick(game.id)
                }
            )
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = modifier
                .padding(8.dp)
        ) {

            AsyncImage(
                model = game.imageURL,
                contentDescription = "Cover of ${game.name}",
                modifier = Modifier
                    .size(size = 100.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = modifier.padding(start = 16.dp)
            ) {

                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = game.genre,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Rating: ${game.rating}",
                    color = Color.Gray
                )
            }
        }
    }
}
