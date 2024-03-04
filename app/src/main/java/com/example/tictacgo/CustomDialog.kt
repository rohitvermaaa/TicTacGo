package com.example.tictacgo

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tictacgo.databinding.CustomDialogSettingsBinding

class CustomDialogSetting(context: Context) : Dialog(context) {
    private var dialogBinding: CustomDialogSettingsBinding =
        CustomDialogSettingsBinding.inflate(layoutInflater)

    init {
        setContentView(dialogBinding.root)
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getBoolean("isDarkMode", false)
        val isSoundOn = sharedPrefs.getBoolean("isSoundOn", true)

        dialogBinding.uiModeSwitchCompat.isChecked = isDarkMode
        updateSoundButton(isSoundOn)

        dialogBinding.uiModeSwitchCompat.setOnCheckedChangeListener { _, isChecked ->
            updateSwitch(isChecked, context)
            dismiss()
        }

        dialogBinding.btnContinue.setOnClickListener {
            dismiss()
        }

        dialogBinding.btnSoundOn.setOnClickListener {
            updateSoundButton(false)
            val editor = sharedPrefs.edit()
            editor.putBoolean("isSoundOn", false)
            editor.apply()
        }

        dialogBinding.btnSoundOff.setOnClickListener {
            updateSoundButton(true)
            val editor = sharedPrefs.edit()
            editor.putBoolean("isSoundOn", true)
            editor.apply()
        }

        dialogBinding.btnExit.setOnClickListener {
            val activity = context as AppCompatActivity
            activity.finishAffinity();
        }
    }

    private fun updateSwitch(isDarkMode: Boolean, context: Context) {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putBoolean("isDarkMode", isDarkMode)
        editor.apply()
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun updateSoundButton(isSoundOn: Boolean) {
        if (isSoundOn) {
            dialogBinding.btnSoundOn.visibility = View.VISIBLE
            dialogBinding.btnSoundOff.visibility = View.INVISIBLE
        } else {
            dialogBinding.btnSoundOn.visibility = View.INVISIBLE
            dialogBinding.btnSoundOff.visibility = View.VISIBLE
        }
    }
}
