package com.mon.gametracker.features.game.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mon.gametracker.features.game.core.domain.game.GetLibraryGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getLibraryGamesUseCase: GetLibraryGamesUseCase
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
}