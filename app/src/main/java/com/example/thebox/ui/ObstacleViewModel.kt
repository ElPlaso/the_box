package com.example.thebox.ui

import androidx.lifecycle.ViewModel
import com.example.thebox.data.DataSource
import com.example.thebox.data.ObstacleUiState
import com.example.thebox.model.Obstacle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ObstacleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ObstacleUiState(
        obstacles = listOf(),
        currentObstacle = null
    ))
    val uiState: StateFlow<ObstacleUiState> = _uiState.asStateFlow()

    private var obstacles: List<Obstacle> = listOf()
    private var currentObstacle: Obstacle? = null

    private fun getObstacles(): List<Obstacle> {
        obstacles = DataSource.obstacles

        return obstacles
    }

    private fun getCurrentObstacle(): Obstacle? {
        return currentObstacle
    }

    private fun selectCurrentObstacle(obstacle: Obstacle) {
        currentObstacle = obstacle

        _uiState.update { currentState ->
            currentState.copy(
                obstacles = obstacles,
                currentObstacle = currentObstacle
            )
        }
    }

    private fun deselectObstacle() {
        _uiState.update { currentState ->
            currentState.copy(
                obstacles = obstacles,
                currentObstacle = null
            )
        }
    }

    init {
        _uiState.update { currentState ->
            currentState.copy(
                obstacles = getObstacles(),
                currentObstacle = null
            )
        }
    }
}