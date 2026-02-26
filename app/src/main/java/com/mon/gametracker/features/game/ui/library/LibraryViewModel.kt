package com.mon.gametracker.features.game.ui.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mon.gametracker.features.game.core.domain.game.GameId
import com.mon.gametracker.features.game.core.domain.game.useCases.DeleteGameUseCase
import com.mon.gametracker.features.game.core.domain.game.useCases.GetLibraryGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getLibraryGamesUseCase: GetLibraryGamesUseCase,
    val deleteGameUseCase: DeleteGameUseCase
) : ViewModel() {
    val uiState: StateFlow<LibraryUiState> = getLibraryGamesUseCase.execute()
        .map { games ->
            LibraryUiState(
                games = games,
                isLoading = false
            )
        }
        .onStart {
            emit(LibraryUiState(isLoading = true))
        }
        .catch { e ->
            emit(LibraryUiState(errorMessage = "Error: ${e.message}", isLoading = false))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LibraryUiState(isLoading = true)
        )

    fun onDeleteGame(gameId: GameId) {
        viewModelScope.launch {
            try {
                deleteGameUseCase.execute(gameId)
            } catch (e: Exception) {
                Log.e("LibraryViewModel", "Error deleting game: ${e.message}")
            }
        }
    }
}