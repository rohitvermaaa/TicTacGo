package com.example.tictacgo

import android.app.Dialog
import android.content.Context
import android.media.MediaPlayer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tictacgo.databinding.CustomDialogSettingsBinding

class CustomDialogSetting(context: Context) : Dialog(context) {
    private var dialogBinding: CustomDialogSettingsBinding =
        CustomDialogSettingsBinding.inflate(layoutInflater)

    private var mediaPlayer: MediaPlayer? = null

    init {
        setContentView(dialogBinding.root)
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isSoundOn = sharedPrefs.getBoolean("isSoundOn", false)

        updateSoundButton(isSoundOn)

        dialogBinding.btnContinue.setOnClickListener {
            dismiss()
        }

        dialogBinding.btnSoundOn.setOnClickListener {
            updateSoundButton(false)
            val editor = sharedPrefs.edit()
            editor.putBoolean("isSoundOn", false)
            editor.apply()
            stopMusic()
        }

        dialogBinding.btnSoundOff.setOnClickListener {
            updateSoundButton(true)
            val editor = sharedPrefs.edit()
            editor.putBoolean("isSoundOn", true)
            editor.apply()
            playMusic()
        }

        dialogBinding.btnExit.setOnClickListener {
            val activity = context as AppCompatActivity
            activity.finishAffinity()
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

    private fun playMusic() {
        mediaPlayer = MediaPlayer.create(context, R.raw.music)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    private fun stopMusic() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
