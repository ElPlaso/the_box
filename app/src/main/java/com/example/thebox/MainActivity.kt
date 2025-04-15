package com.example.thebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.example.thebox.ui.ObstacleViewModel
import com.example.thebox.ui.theme.TheBoxTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thebox.ui.ObstacleScreen
import com.example.thebox.ui.ObstacleSelectScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheBoxTheme {
                App(
                )
            }
        }
    }
}

enum class Screen() {
    ObstacleSelect,
    Obstacle
}

@Composable
fun App(
    viewModel: ObstacleViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.ObstacleSelect.name,
    ) {
        composable(route = Screen.ObstacleSelect.name) {
            ObstacleSelectScreen(
                obstacles = uiState.obstacles,
                onObstacleSelect = {
                    viewModel.selectObstacle(it)
                    navController.navigate(Screen.Obstacle.name)
                }
            )
        }
        composable(route = Screen.Obstacle.name) {
            ObstacleScreen(
                obstacle = uiState.currentObstacle
            )
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    TheBoxTheme {
        App()
    }
}
