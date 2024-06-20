package com.example.game

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appgame.Question
import com.example.appgame.QuestionAdapter

class FinishActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val score = intent.getIntExtra("score", 0)
        val data: ArrayList<Question> = intent.getSerializableExtra("dataset") as ArrayList<Question>

        val tvScore: TextView = findViewById(R.id.tvScore)
        tvScore.text = "Your Score \n$score/10"

        val highScore = saveHighScore(score)
        val tvHighScore: TextView = findViewById(R.id.tvHighScore)
        tvHighScore.text = "High Score: $highScore/10"
        setAdapterRecyclerview(data)

        val btnHome: Button = findViewById(R.id.btnHome)
        btnHome.setOnClickListener { finish() }
    }

    private fun saveHighScore(score: Int): Int {
        sharedPreferences = getSharedPreferences("HighScores", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentHighScore = sharedPreferences.getInt("highScore", 0)

        if (score > currentHighScore) {
            editor.putInt("highScore", score)
            editor.apply()
            return score
        }

        return currentHighScore
    }

    private fun setAdapterRecyclerview(data: ArrayList<Question>) {
        val recyclerView: RecyclerView = findViewById(R.id.rvQuestionList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = QuestionAdapter(data)
        recyclerView.adapter = adapter
    }
}
