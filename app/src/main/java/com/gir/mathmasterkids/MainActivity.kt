package com.gir.mathmasterkids

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.SharedPreferences

class MainActivity : AppCompatActivity() {
    var correctAnswer = 0
    var score = 0
    var level = 1
    lateinit var timer: CountDownTimer
    lateinit var correctSound: MediaPlayer
    lateinit var sharedPref: SharedPreferences
    var highScore = 0
    lateinit var highScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        generateQuestion()
        startTimer()

        val submitBtn = findViewById<Button>(R.id.submitBtn)
        correctSound = MediaPlayer.create(this, R.raw.correct)
        sharedPref = getSharedPreferences("MyApp", MODE_PRIVATE)
        val resetScoreBtn = findViewById<Button>(R.id.resetScoreBtn)
        val resetLevelBtn = findViewById<Button>(R.id.resetLevelBtn)

        highScoreText = findViewById(R.id.highScoreText)
        highScore = sharedPref.getInt("highScore", 0)
        highScoreText.text = "🔥 High Score: $highScore"

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
                if (score > highScore) {
                    highScore = score
                    highScoreText.text = "🔥 High Score: $highScore"

                    sharedPref.edit().putInt("highScore", highScore).apply()
                }

            } else {
                resultText.text = "Wrong ❌"
            }

            if (score >= 5) {
                level = 2
            }
            if (score >= 10) {
                level = 3
            }

            val editor = sharedPref.edit()
            editor.putInt("score", score)
            editor.putInt("level", level)
            editor.apply()

            val scoreText = findViewById<TextView>(R.id.scoreText)
            scoreText.text = "Score: $score"

            val levelText = findViewById<TextView>(R.id.levelText)
            levelText.text = "Level: $level"

            timer.cancel()
            startTimer()

            generateQuestion()
            answerInput.text.clear()
        }

        score = sharedPref.getInt("score", 0)
        level = sharedPref.getInt("level", 1)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val levelText = findViewById<TextView>(R.id.levelText)
        scoreText.text = "⭐ Score: $score"
        levelText.text = "🏆 Level: $level"

        resetScoreBtn.setOnClickListener {
            score = 0
            scoreText.text = "⭐ Score: $score"

            sharedPref.edit().putInt("score", score).apply()
        }

        resetLevelBtn.setOnClickListener {
            level = 1
            levelText.text = "🏆 Level: $level"

            sharedPref.edit().putInt("level", level).apply()
        }

        highScore = sharedPref.getInt("highScore", 0)

        val highScoreText = findViewById<TextView>(R.id.highScoreText)
        highScoreText.text = "🔥 High Score: $highScore"
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

                val intent = Intent(this@MainActivity, GameOverActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("highScore", highScore)

                startActivity(intent)
                finish()
            }

        }.start()
    }
}