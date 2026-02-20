package com.mon.gametracker.features.game.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.achievement.GetAchievementUseCase
import com.mon.gametracker.features.game.core.domain.achievement.SetAchievementCompletedUseCase
import com.mon.gametracker.features.game.core.domain.game.AddGameToLibraryUseCase
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.GetGameUseCase
import com.mon.gametracker.navigation.DetailSource
import com.mon.gametracker.navigation.GameDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAchievementUseCase: GetAchievementUseCase,
    private val setAchievementCompletedUseCase: SetAchievementCompletedUseCase,
    private val getGameUseCase: GetGameUseCase,
    private val addGameToLibraryUseCase: AddGameToLibraryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route = savedStateHandle.toRoute<GameDetailDestination>()
    private val gameId = GameId(route.gameId)


    private val _uiState = MutableStateFlow(
        value = DetailUiState(
            canEditAchievements = route.source == DetailSource.LIBRARY,
            showAddButton = route.source == DetailSource.SEARCH_API
        )
    )

    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        loadGameData()
    }

    private fun loadGameData() {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            val gameId = GameId(value = gameId.value)

            val result = runCatching {
                coroutineScope {
                    val gameDeferred = async { getGameUseCase.execute(gameId) }
                    val achievementsDeferred = async { getAchievementUseCase.execute(gameId) }

                    Pair(gameDeferred.await(), achievementsDeferred.await())
                }
            }

            result.onSuccess { (game, achievements) ->
                _uiState.update {
                    it.copy(
                        game = game,
                        achievements = achievements,
                        isLoading = false
                    )
                }
            }.onFailure { e ->
                Log.e("DetailViewModel", "Error loading data", e)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.localizedMessage ?: "Unknown error"
                    )
                }
            }
        }

    }

    fun onToggleAchievement(
        achievementId: AchievementId,
        isCompleted: Boolean
    ) {

        if (!_uiState.value.canEditAchievements) return

        val gameId = GameId("1")

        viewModelScope.launch {
            val previous = _uiState.value.achievements

            _uiState.update { state ->
                state.copy(
                    achievements = state.achievements.map { achievement ->
                        if (achievement.key.achievementId == achievementId) {
                            achievement.copy(
                                isCompleted = isCompleted,
                                completionDate = if (isCompleted) LocalDate.now() else null
                            )
                        } else achievement
                    }
                )
            }

            runCatching {
                setAchievementCompletedUseCase.execute(
                    gameId = gameId,
                    achievementId = achievementId,
                    isCompleted = isCompleted
                )
            }.onFailure {
                _uiState.update {
                    it.copy(
                        achievements = previous
                    )
                }
            }
        }
    }

    fun onShowAddConfirmation() {
        _uiState.update { it.copy(showConfirmDialog = true) }
    }

    fun onDismissDialog() {
        _uiState.update { it.copy(showConfirmDialog = false) }
    }

    fun onConfirmAddGame() {
        val game = _uiState.value.game ?: return

        _uiState.update { it.copy(showConfirmDialog = false) }

        viewModelScope.launch {
            runCatching {
                addGameToLibraryUseCase.execute(game)
            }.onSuccess {
                _uiState.update { it.copy(showAddButton = false) }
            }
        }
    }

}
