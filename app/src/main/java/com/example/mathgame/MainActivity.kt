package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.Button


class MainActivity : AppCompatActivity() {

//    private lateinit var rvLeaderboard: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_addition).setOnClickListener {
            startActivity(Intent(this, AdditionActivity::class.java))
        }

        findViewById<Button>(R.id.button_subtraction).setOnClickListener {
            startActivity(Intent(this, SubtractionActivity::class.java))
        }

        findViewById<Button>(R.id.button_multiplication).setOnClickListener {
            startActivity(Intent(this, MultiplicationActivity::class.java))
        }

//        rvLeaderboard = findViewById(R.id.rv_leaderboard)
//        rvLeaderboard.layoutManager = LinearLayoutManager(this)
//
//        loadHighScores()
    }

//    private fun loadHighScores() {
//        val scoreDao = AppDatabase.getDatabase(this).scoreDao()
//        GlobalScope.launch {
//            val highScores = scoreDao.getHighScores()
//            runOnUiThread {
//                rvLeaderboard.adapter = ScoreAdapter(highScores)
//            }
//        }
//    }
}
