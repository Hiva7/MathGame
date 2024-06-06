package com.example.mathgame

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: Score)

    @Query("SELECT * FROM Score ORDER BY score DESC")
    suspend fun getHighScores(): List<Score>
}
