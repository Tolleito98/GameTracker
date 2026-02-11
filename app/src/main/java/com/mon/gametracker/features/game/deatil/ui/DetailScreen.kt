package com.mon.gametracker.features.game.deatil.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.mon.gametracker.core.ui.components.AppTopBar
import com.mon.gametracker.features.game.deatil.ui.components.AchievementItem
import com.mon.gametracker.features.game.domain.achievement.Achievement
import com.mon.gametracker.features.game.domain.achievement.AchievementId
import com.mon.gametracker.features.game.domain.game.Game
import com.mon.gametracker.features.game.domain.game.GameId


@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {
//tempGame
    val temp = Game(
        id = GameId("1"),
        name = "zelda",
        imageURL = "",
        genre = "adventure",
        developer = "nintendo",
        rating = 5.5,

        )

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Detail Screen",
            )
        },
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            DetailContent(
                modifier = Modifier.padding(paddingValues),
                game = temp,
                achievements = uiState.achievements,
                onToggleAchievement = { achievementId, isCompleted ->
                    viewModel.onToggleAchievement(
                        achievementId = achievementId,
                        isCompleted = isCompleted
                    )
                }
            )
        }
    }
}


@Composable
private fun DetailContent(
    game: Game,
    achievements: List<Achievement>,
    onToggleAchievement: (AchievementId, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            AsyncImage(
                model = "https://picsum.photos/400/600",
                contentDescription = "Cover of ${"GameName"}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Spacer(modifier = Modifier.padding(vertical = 14.dp))
            Text(
                text = "Name",
                style = MaterialTheme.typography.headlineMedium
            )
            DetailItem(
                label = "Developer",
                value = game.developer
            )
            DetailItem(
                label = "Genre",
                value = game.genre
            )

        }

        items(
            items = achievements,
            key = { it.key.achievementId.value }
        ) { achievement ->
            AchievementItem(
                achievement = achievement,
                onToggle = { newValue ->
                    onToggleAchievement(
                        achievement.key.achievementId,
                        newValue
                    )
                }
            )
        }
    }
}


@Composable
private fun DetailItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

