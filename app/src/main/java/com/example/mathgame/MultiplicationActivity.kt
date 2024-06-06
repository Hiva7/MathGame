package com.example.mathgame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MultiplicationActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var tvLife: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvCalculation: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnConfirm: Button

    private var score = 0
    private var life = 3
    private var timeLeft = 60
    private var num1 = 0
    private var num2 = 0

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication)

        tvScore = findViewById(R.id.tv_score)
        tvLife = findViewById(R.id.tv_life)
        tvTime = findViewById(R.id.tv_time)
        tvCalculation = findViewById(R.id.tv_calculation)
        etAnswer = findViewById(R.id.et_answer)
        btnConfirm = findViewById(R.id.btn_confirm)

        startGame()

        btnConfirm.setOnClickListener {
            checkAnswer()
        }
    }

    private fun startGame() {
        generateCalculation()
        startTimer()
    }

    private fun generateCalculation() {
        num1 = Random.nextInt(1, 11)
        num2 = Random.nextInt(1, 11)
        tvCalculation.text = "$num1 * $num2 = ???"
    }

    private fun startTimer() {
        timer = object : CountDownTimer((timeLeft * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
                tvTime.text = "Time: $timeLeft"
            }

            override fun onFinish() {
                gameOver()
            }
        }.start()
    }

    private fun checkAnswer() {
        val answer = etAnswer.text.toString().toIntOrNull()
        if (answer == null) {
            etAnswer.error = "Enter a valid number"
            return
        }

        if (answer == num1 * num2) {
            score += timeLeft
            tvScore.text = "Score: $score"
            generateCalculation()
            timer.cancel()
            timeLeft = 60
            startTimer()
        } else {
            life--
            tvLife.text = "Life: $life"
            if (life == 0) {
                gameOver()
            }
        }

        etAnswer.text.clear()
    }

    private fun gameOver() {
        timer.cancel()
        saveScore()
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Game Over")
        alertDialog.setMessage("Your Score: $score")
        alertDialog.setPositiveButton("OK") { _, _ ->
            finish()
        }
        alertDialog.show()
    }

    private fun saveScore() {
        val scoreDao = AppDatabase.getDatabase(this).scoreDao()
        val scoreEntity = Score(category = "Multiplication", score = score)
        GlobalScope.launch {
            scoreDao.insert(scoreEntity)
        }
    }
}
