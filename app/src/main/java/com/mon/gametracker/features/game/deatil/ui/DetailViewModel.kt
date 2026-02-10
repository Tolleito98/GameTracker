package com.mon.gametracker.features.game.deatil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mon.gametracker.features.game.domain.achievement.GetAchievementUseCase
import com.mon.gametracker.features.game.domain.game.GameId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAchievementUseCase: GetAchievementUseCase,
    //todo: definir el getGame use case
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = DetailUiState())
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
            try {
                //todo: pasar el game id por navegación
                val achievements = getAchievementUseCase.execute(GameId("1"))
                _uiState.value = _uiState.value.copy(
                    achievements = achievements,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error ${e.message}?: unknown",
                    isLoading = false
                )
            }
        }

    }

}
