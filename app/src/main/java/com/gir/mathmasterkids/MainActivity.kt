package com.gir.mathmasterkids

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var correctAnswer = 0
    var score = 0
    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        generateQuestion()
        startTimer()

        val submitBtn = findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener {
            val answerInput = findViewById<EditText>(R.id.answerInput)
            val resultText = findViewById<TextView>(R.id.resultText)

            val userAnswerText = answerInput.text.toString()

            if (userAnswerText.isEmpty()) {
                resultText.text = "Enter answer first!"
                return@setOnClickListener
            }

            val userAnswer = userAnswerText.toInt()

            if (userAnswer == correctAnswer) {
                score++
                resultText.text = "Correct ✅"
            } else {
                resultText.text = "Wrong ❌"
            }

            val scoreText = findViewById<TextView>(R.id.scoreText)
            scoreText.text = "Score: $score"

            timer.cancel()
            startTimer()

            generateQuestion()
            answerInput.text.clear()
        }

    }

    fun generateQuestion() {
        val a = (0..9).random()
        val b = (0..9).random()

        correctAnswer = a + b

        val questionText = findViewById<TextView>(R.id.questionText)
        questionText.text = "$a + $b = ?"
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