package com.example.thebox.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thebox.R
import com.example.thebox.model.Obstacle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObstacleSelectScreen(
    obstacles: List<Obstacle>,
    onObstacleSelect: (Obstacle) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 8.dp),
                title = { Text(stringResource(R.string.title)) },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            Text(
                stringResource(R.string.select_a_surface),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            ObstacleList(obstacles, onObstacleSelect = onObstacleSelect)
        }
    }
}

@Composable
fun ObstacleList(
    obstacles: List<Obstacle>,
    onObstacleSelect: (Obstacle) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(obstacles) { obstacle ->
            ObstacleCard(
                obstacle = obstacle,
                onSelect = onObstacleSelect,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun ObstacleCard(
    obstacle: Obstacle,
    onSelect: (Obstacle) -> Unit,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(obstacle.resource)

    Card(
        onClick = { onSelect(obstacle) },
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painter,
                contentDescription = obstacle.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(80.dp)
            )
            Text(
                text = obstacle.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


