package com.example.thebox.data

import com.example.thebox.R
import com.example.thebox.model.Obstacle

object DataSource {
    val obstacles = listOf(
        Obstacle("Plywood", R.drawable.plywood),
        Obstacle("Green Wood", R.drawable.green_wood),
        Obstacle("Stone Gap", R.drawable.stones),
        Obstacle("Brick", R.drawable.brick),
        Obstacle("Grass Gap", R.drawable.grass),
        Obstacle("Concrete", R.drawable.concrete),
        Obstacle("Paint", R.drawable.paint),
        Obstacle("Tiles", R.drawable.tiles),
    )
}