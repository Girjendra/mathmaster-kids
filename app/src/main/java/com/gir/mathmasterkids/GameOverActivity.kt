package com.gir.mathmasterkids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_over)

        val finalScoreText = findViewById<TextView>(R.id.finalScoreText)
        val highScoreText = findViewById<TextView>(R.id.highScoreText)
        val restartBtn = findViewById<Button>(R.id.restartBtn)

        val score = intent.getIntExtra("score", 0)
        val highScore = intent.getIntExtra("highScore", 0)

        finalScoreText.text = "Score: $score"
        highScoreText.text = "High Score: $highScore"

        restartBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}