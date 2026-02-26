package com.mon.gametracker.features.game.ui.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mon.gametracker.features.game.core.domain.game.useCases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddScreenUiState())
    val uiState: StateFlow<AddScreenUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        fetchInitialGames()
    }

    fun fetchInitialGames() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            val games = runCatching {
                getGamesUseCase.execute()
            }

            games.onSuccess { games ->
                _uiState.update {
                    it.copy(
                        games = games,
                        isLoading = false
                    )
                }
            }.onFailure { e ->
                Log.e("AddViewModel", "Error loading data", e)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    fun onSearchChange(newQuery: String) {
        _uiState.update {
            it.copy(
                searchQuery = newQuery
            )
        }

        searchJob?.cancel()

        if (newQuery.length < 3) {
            _uiState.update { it.copy(suggestions = emptyList(), isSearchingSuggestions = false) }
            return
        }

        searchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isSearchingSuggestions = true
                )
            }

            delay(400)

            val results = runCatching {
                getGamesUseCase.execute(query = newQuery)
            }

            results.onSuccess { games ->
                _uiState.update {
                    it.copy(
                        isSearchingSuggestions = false,
                        suggestions = games.take(3),
                    )
                }


            }.onFailure { e ->
                Log.e("AddViewModel", "Error searching data", e)
                _uiState.update {
                    it.copy(
                        isSearchingSuggestions = false,
                    )
                }
            }
        }
    }

    fun onSearchConfirmed() {
        searchJob?.cancel()
        val query = _uiState.value.searchQuery

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    suggestions = emptyList(),
                    isSearchingSuggestions = false
                )
            }
            val results = runCatching {
                getGamesUseCase.execute(query = query)
            }

            results.onSuccess { games ->
                _uiState.update {
                    it.copy(
                        games = games,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }.onFailure { e ->
                Log.e("AddViewModel", "Error in confirmed search", e)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Error al buscar juegos"
                    )
                }
            }
        }
    }

}