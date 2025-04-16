package com.example.thebox.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.thebox.model.Obstacle

@Composable
fun ObstacleScreen(
    obstacle: Obstacle?,
    modifier: Modifier = Modifier
) {
    if (obstacle != null) {
        ObstacleImage(obstacle, modifier = modifier.fillMaxHeight())
    }
}

@Composable
fun ObstacleImage(
    obstacle: Obstacle,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(obstacle.resource)

    Image(
        painter = painter,
        contentDescription = obstacle.name,
        contentScale = ContentScale.FillHeight,
        modifier = modifier
    )
}


