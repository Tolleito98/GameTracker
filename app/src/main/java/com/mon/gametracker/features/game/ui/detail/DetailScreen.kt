package com.mon.gametracker.features.game.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.mon.gametracker.core.ui.components.AppTopBar
import com.mon.gametracker.core.ui.components.ErrorCard
import com.mon.gametracker.features.game.ui.detail.components.AchievementItem
import com.mon.gametracker.features.game.ui.detail.components.EmptyAchievementsCard
import com.mon.gametracker.features.game.core.domain.achievement.Achievement
import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.game.Game


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val (game, isLoading, errorMessage, showConfirmDialog, achievements, isEditable, showAddButton) = uiState

    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onDismissDialog() },
            confirmButton = {
                TextButton(onClick = { viewModel.onConfirmAddGame() }) {
                    Text("Añadir")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onDismissDialog() }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Añadir a la biblioteca") },
            text = { Text("¿Quieres añadir ${uiState.game?.name} a tu colección de juegos?") }
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Game Detail",
                onBack = onBack
            )
        },
        floatingActionButton = {
            if (showAddButton) {
                FloatingActionButton(
                    onClick = {viewModel.onShowAddConfirmation() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Add game"
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                errorMessage != null -> {
                    ErrorCard(
                        message = errorMessage,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                game != null -> {
                    DetailContent(
                        game = game,
                        achievements = achievements,
                        onToggleAchievement = { achievementId, isCompleted ->
                            viewModel.onToggleAchievement(
                                achievementId = achievementId,
                                isCompleted = isCompleted,
                            )
                        },
                        isEditable = isEditable,
                    )
                }
            }
        }
    }
}


@Composable
private fun DetailContent(
    isEditable: Boolean,
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
                model = game.imageURL,
                contentDescription = "Cover of ${game.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Spacer(modifier = Modifier.padding(vertical = 14.dp))
            Text(
                text = game.name,
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

        if (achievements.isEmpty()) {
            item {
                EmptyAchievementsCard()
            }
        } else {

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
                    },
                    isEditable = isEditable,
                )
            }
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

