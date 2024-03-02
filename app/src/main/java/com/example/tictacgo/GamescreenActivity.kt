package com.example.tictacgo

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tictacgo.databinding.ActivityGamescreenBinding

class GamescreenActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var binding: ActivityGamescreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        updateSwitch()

        binding.uiModeSwitchCompat.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
            )
            saveMode(isChecked)
        }
    }

    private fun updateSwitch() {
        val isDarkMode = sharedPrefs.getBoolean("isDarkMode", false)
        binding.uiModeSwitchCompat.isChecked = isDarkMode
    }

    private fun saveMode(isDarkMode: Boolean) {
        sharedPrefs.edit().putBoolean("isDarkMode", isDarkMode).apply()
    }
}
