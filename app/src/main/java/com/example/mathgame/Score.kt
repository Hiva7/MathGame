package com.example.mathgame

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val score: Int
)
