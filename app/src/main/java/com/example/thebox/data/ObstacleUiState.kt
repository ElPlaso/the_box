package com.example.thebox.data

import com.example.thebox.model.Obstacle

data class ObstacleUiState(
    val obstacles: List<Obstacle>,
    val currentObstacle: Obstacle?,
)
