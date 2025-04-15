package com.example.thebox.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    LazyColumn(modifier = modifier) {
        items(obstacles) { obstacle ->
            ObstacleCard(
                obstacle = obstacle,
                onSelect = onObstacleSelect,
                modifier = Modifier.padding(8.dp)
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
                contentDescription = obstacle.name
            )
            Text(
                text = obstacle.name.uppercase(),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


