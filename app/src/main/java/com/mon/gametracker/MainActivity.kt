package com.mon.gametracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.mon.gametracker.core.ui.theme.GameTrackerTheme
import com.mon.gametracker.features.game.ui.add.AddScreen
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
