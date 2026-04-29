package com.gir.mathmasterkids

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context

class MainActivity : AppCompatActivity() {
    var correctAnswer = 0
    var score = 0
    var level = 1
    lateinit var timer: CountDownTimer
    lateinit var correctSound: MediaPlayer
    lateinit var wrongSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        generateQuestion()
        startTimer()

        val submitBtn = findViewById<Button>(R.id.submitBtn)

        correctSound = MediaPlayer.create(this, R.raw.correct)
        wrongSound = MediaPlayer.create(this, R.raw.wrong)

        submitBtn.setOnClickListener {
            val answerInput = findViewById<EditText>(R.id.answerInput)
            val resultText = findViewById<TextView>(R.id.resultText)

            val userAnswerText = answerInput.text.toString()

            if (userAnswerText.isEmpty()) {
                resultText.text = "Enter answer first!"
                return@setOnClickListener
            }

            val userAnswer = userAnswerText.toIntOrNull()

            if (userAnswer == null) {
                resultText.text = "Enter valid integer!"
                return@setOnClickListener
            }

            if (userAnswer == correctAnswer) {
                score++
                resultText.text = "Correct ✅"
                correctSound.start()
            } else {
                resultText.text = "Wrong ❌"
                wrongSound.start()
            }

            if (score >= 5) {
                level = 2
            }
            if (score >= 10) {
                level = 3
            }

            val scoreText = findViewById<TextView>(R.id.scoreText)
            scoreText.text = "Score: $score"

            val levelText = findViewById<TextView>(R.id.levelText)
            levelText.text = "Level: $level"

            timer.cancel()
            startTimer()

            generateQuestion()
            answerInput.text.clear()
        }

    }

    fun generateQuestion() {

        val questionText = findViewById<TextView>(R.id.questionText)

        val (min, max) = when(level) {
            1 -> Pair(1, 10)
            2 -> Pair(10, 21)
            else -> Pair(20, 51)
        }

        val a = (min until max).random()
        val b = (min until max).random()

        val operations = listOf("+", "-", "*", "/")
        val op = operations.random()

        when(op) {

            "+" -> {
                correctAnswer = a + b
                questionText.text = "$a + $b = ?"
            }

            "-" -> {
                correctAnswer = a - b
                questionText.text = "$a - $b = ?"
            }

            "*" -> {
                correctAnswer = a * b
                questionText.text = "$a × $b = ?"
            }

            "/" -> {
                correctAnswer = b   // because (a*b)/a = b
                val dividend = a * b
                questionText.text = "$dividend ÷ $a = ?"
            }
        }
    }

    fun startTimer() {

        val timerText = findViewById<TextView>(R.id.timerText)

        timer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timerText.text = "Time's Up!"
            }

        }.start()
    }
}