package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.game.databinding.ActivityHomeBinding
import com.example.game.databinding.ActivityMainBinding

class Home : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnEasy?.setOnClickListener{startGame("easy")}
        binding?.btnMedium?.setOnClickListener{startGame("medium")}
        binding?.btnHard?.setOnClickListener { startGame("hard") }
    }

    private fun startGame(questionType: String)
    {
        val intent = Intent(this,PlayActivity::class.java)
        intent.putExtra("questionType",questionType)
        startActivity(intent)
    }

}