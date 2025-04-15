package com.example.thebox.data

import com.example.thebox.R
import com.example.thebox.model.Obstacle

object DataSource {
    val obstacles = listOf(
        Obstacle("plywood", R.drawable.plywood),
        Obstacle("green wood", R.drawable.green_wood),
        Obstacle("stone gap", R.drawable.stones),
        Obstacle("brick", R.drawable.brick),
        Obstacle("grass gap", R.drawable.grass),
        Obstacle("concrete", R.drawable.concrete),
        Obstacle("paint", R.drawable.paint),
        Obstacle("tiles", R.drawable.tiles),
    )
}