package com.example.tictacgo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictacgo.databinding.ActivityGamescreenBinding

class GamescreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamescreenBinding
    private var noOfXWins : Int = 0
    private var noOfDraws : Int = 0
    private var noOf0wins : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResetScore.setOnClickListener {
            resetScores()
        }
    }

    private fun resetScores() {
        noOfXWins = 0
        noOfDraws = 0
        noOf0wins = 0
        binding.tvNumberOfXWins.text = "$noOfXWins Wins"
        binding.tvNumberOfDraws.text = "$noOfDraws Wins"
        binding.tvNumberOfOWins.text = "$noOf0wins Wins"
    }

}
