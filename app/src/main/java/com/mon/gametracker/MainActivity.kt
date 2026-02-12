package com.mon.gametracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mon.gametracker.core.ui.theme.GameTrackerTheme
import com.mon.gametracker.features.game.deatil.ui.DetailScreen
import com.mon.gametracker.features.game.library.ui.LibraryScreen
import com.mon.gametracker.navigation.GameNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameTrackerTheme {
                GameNavHost(navController = rememberNavController())
            }
        }
    }
}
