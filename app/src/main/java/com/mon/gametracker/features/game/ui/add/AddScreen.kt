package com.mon.gametracker.features.game.ui.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mon.gametracker.core.ui.components.AppTopBar
import com.mon.gametracker.core.ui.components.ErrorCard
import com.mon.gametracker.features.game.core.domain.game.GameSummary
import com.mon.gametracker.features.game.ui.components.GameSearchBar
import com.mon.gametracker.features.game.ui.components.GameList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onBack: () -> Unit,
    
    viewModel: AddScreenViewModel = hiltViewModel()
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val uiState by viewModel.uiState.collectAsState()
    var isExpanded by remember { mutableStateOf(false) }

    val (
        searchQuery,
        games,
        errorMessage,
        isLoading,
        suggestions,
        isSearchingSuggestions
    ) = uiState

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AddScreenTopBar(
                scrollBehavior = scrollBehavior,
                isExpanded = isExpanded,
                onExpandedChange = { isExpanded = it },
                searchQuery = searchQuery,
                suggestions = suggestions,
                isSearching = isSearchingSuggestions,
                onQueryChange = viewModel::onSearchChange,
                onSearchConfirmed = viewModel::onSearchConfirmed
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                errorMessage != null -> {
                    ErrorCard(
                        message = errorMessage,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    GameList(
                        games = games,
                        onGameClick = { /* TODO: Guardar juego */ }
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    searchQuery: String,
    suggestions: List<GameSummary>,
    isSearching: Boolean,
    onQueryChange: (String) -> Unit,
    onSearchConfirmed: () -> Unit
) {

    Column {
        if (!isExpanded) {
            AppTopBar(
                title = "Add Games",
                scrollBehavior = scrollBehavior
            )
        }

        Surface(
            tonalElevation = 3.dp,
            color = MaterialTheme.colorScheme.surface,
        ) {

            GameSearchBar(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                query = searchQuery,
                onQueryChange = onQueryChange,
                onSearch = {
                    onExpandedChange(false)
                    onSearchConfirmed()
                },
                expanded = isExpanded,
                onExpandedChange = onExpandedChange,
                suggestions = suggestions,
                isSearching = isSearching,
                onSuggestionClick = { game ->
                    onExpandedChange(false)
                    onQueryChange(game.name)
                    onSearchConfirmed()
                }
            )
        }
    }
}
