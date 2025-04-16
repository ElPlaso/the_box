package com.example.thebox

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheBoxTheme {
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                App()
            }
        }
    }
}

enum class Screen {
    ObstacleSelect,
    Obstacle
}

@Composable
fun App(
    viewModel: ObstacleViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    navController.addOnDestinationChangedListener { _, destination, _ ->
        if (destination.route == Screen.ObstacleSelect.name) {
            viewModel.deselectObstacle()
        }
    }

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

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Preview
@Composable
fun AppPreview() {
    TheBoxTheme {
        App()
    }
}
