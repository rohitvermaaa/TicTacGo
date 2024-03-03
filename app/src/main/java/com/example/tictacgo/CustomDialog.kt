package com.example.tictacgo

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.tictacgo.databinding.CustomDialogSettingsBinding

class CustomDialogSetting(context: Context) : Dialog(context) {
    private var dialogBinding: CustomDialogSettingsBinding = CustomDialogSettingsBinding.inflate(layoutInflater)

    init {
        setContentView(dialogBinding.root)
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getBoolean("isDarkMode", false)
        dialogBinding.uiModeSwitchCompat.isChecked = isDarkMode

        dialogBinding.uiModeSwitchCompat.setOnCheckedChangeListener { _, isChecked ->
            updateSwitch(isChecked, context)
            dismiss()
        }

        dialogBinding.btnContinue.setOnClickListener {
            dismiss()
        }
        dialogBinding.btnSoundOn.setOnClickListener {
            dialogBinding.btnSoundOn.visibility = View.INVISIBLE
            dialogBinding.btnSoundOff.visibility = View.VISIBLE
        }

        dialogBinding.btnSoundOff.setOnClickListener {
            dialogBinding.btnSoundOn.visibility = View.VISIBLE
            dialogBinding.btnSoundOff.visibility = View.INVISIBLE
        }

        dialogBinding.btnHome.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun updateSwitch(isDarkMode: Boolean, context: Context) {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putBoolean("isDarkMode", isDarkMode)
        editor.apply()
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
