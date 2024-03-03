package com.example.tictacgo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tictacgo.databinding.ActivityGamescreenBinding

class GamescreenActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var binding: ActivityGamescreenBinding
    private var noOfXWins: Int = 0
    private var noOfDraws: Int = 0
    private var noOf0wins: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.btnResetScore.setOnClickListener {
            resetScores()
        }

        binding.btnSetting.setOnClickListener {
            showSettingsDialog()
        }
    }

    private fun resetScores() {
        noOfXWins = 0
        noOfDraws = 0
        noOf0wins = 0
        updateScoreText()
    }

    private fun updateScoreText() {
        binding.tvNumberOfXWins.text = "$noOfXWins Wins"
        binding.tvNumberOfDraws.text = "$noOfDraws Wins"
        binding.tvNumberOfOWins.text = "$noOf0wins Wins"
    }

    private fun showSettingsDialog() {
        val dialog = CustomDialogSetting(this)
        dialog.show()
    }
}
