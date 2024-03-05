package com.example.tictacgo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tictacgo.databinding.ActivityGamescreenBinding

class GamescreenActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var binding: ActivityGamescreenBinding
    private var noOfXWins: Int = 0
    private var noOfDraws: Int = 0
    private var noOf0wins: Int = 0
    private val boardKey = "board_state"
    private val chanceKey = "chance_state"
    private val defaultCellValue = 2  // Default value for an empty cell
    private val board = IntArray(9) { defaultCellValue }  // board state
    var chance : Int = 1    //0 mean O and 1 mean X 2 mean empty
    private val handler = Handler(Looper.getMainLooper())

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

        clickListeners()

        // Set UI mode switch state
        binding.uiModeSwitchCompat.isChecked = isDarkModeEnabled()

        binding.uiModeSwitchCompat.setOnCheckedChangeListener { _, isChecked ->
            updateUIMode(isChecked)
        }

        // Initialize board and chance if savedInstanceState is null
        if (savedInstanceState == null) {
            resetBoardUI()
        } else {
            restoreBoardState(savedInstanceState)
        }
    }


    private fun isDarkModeEnabled(): Boolean {
        return sharedPrefs.getBoolean("isDarkMode", false)
    }

    private fun updateUIMode(isDarkMode: Boolean) {
        val editor = sharedPrefs.edit()
        editor.putBoolean("isDarkMode", isDarkMode)
        editor.apply()

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun clickListeners() {
        binding.cell1.setOnClickListener {
            if (board[0] == 2) {
                if (chance == 1) {
                    binding.cell1x.visibility = View.VISIBLE
                    chance = 0
                    board[0] = 1
                } else {
                    binding.cell1o.visibility = View.VISIBLE
                    chance = 1
                    board[0] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell2.setOnClickListener {
            println(board)
            if (board[1] == 2) {
                if (chance == 1) {
                    binding.cell2x.visibility = View.VISIBLE
                    chance = 0
                    board[1] = 1
                } else {
                    binding.cell2o.visibility = View.VISIBLE
                    chance = 1
                    board[1] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell3.setOnClickListener {
            if (board[2] == 2) {
                if (chance == 1) {
                    binding.cell3x.visibility = View.VISIBLE
                    chance = 0
                    board[2] = 1
                } else {
                    binding.cell3o.visibility = View.VISIBLE
                    chance = 1
                    board[2] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell4.setOnClickListener {
            if (board[3] == 2) {
                if (chance == 1) {
                    binding.cell4x.visibility = View.VISIBLE
                    chance = 0
                    board[3] = 1
                } else {
                    binding.cell4o.visibility = View.VISIBLE
                    chance = 1
                    board[3] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell5.setOnClickListener {
            if (board[4] == 2) {
                if (chance == 1) {
                    binding.cell5x.visibility = View.VISIBLE
                    chance = 0
                    board[4] = 1
                } else {
                    binding.cell5o.visibility = View.VISIBLE
                    chance = 1
                    board[4] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell6.setOnClickListener {
            if (board[5] == 2) {
                if (chance == 1) {
                    binding.cell6x.visibility = View.VISIBLE
                    chance = 0
                    board[5] = 1
                } else {
                    binding.cell6o.visibility = View.VISIBLE
                    chance = 1
                    board[5] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell7.setOnClickListener {
            if (board[6] == 2) {
                if (chance == 1) {
                    binding.cell7x.visibility = View.VISIBLE
                    chance = 0
                    board[6] = 1
                } else {
                    binding.cell7o.visibility = View.VISIBLE
                    chance = 1
                    board[6] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell8.setOnClickListener {
            if (board[7] == 2) {
                if (chance == 1) {
                    binding.cell8x.visibility = View.VISIBLE
                    chance = 0
                    board[7] = 1
                } else {
                    binding.cell8o.visibility = View.VISIBLE
                    chance = 1
                    board[7] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }

        binding.cell9.setOnClickListener {
            if (board[8] == 2) {
                if (chance == 1) {
                    binding.cell9x.visibility = View.VISIBLE
                    chance = 0
                    board[8] = 1
                } else {
                    binding.cell9o.visibility = View.VISIBLE
                    chance = 1
                    board[8] = 0
                }
                updateChanceImage()
                checkWinner()
            }
        }
    }

    private fun resetScores() {
        noOfXWins = 0
        noOfDraws = 0
        noOf0wins = 0
        chance = 1
        updateChanceImage()
        updateScoreText()
        resetBoardUI()
        for (i in board.indices){
            board[i] = 2
        }
    }

    private fun resetBoardUI() {
        val cells = listOf(
            binding.cell1x, binding.cell1o,
            binding.cell2x, binding.cell2o,
            binding.cell3x, binding.cell3o,
            binding.cell4x, binding.cell4o,
            binding.cell5x, binding.cell5o,
            binding.cell6x, binding.cell6o,
            binding.cell7x, binding.cell7o,
            binding.cell8x, binding.cell8o,
            binding.cell9x, binding.cell9o
        )

        cells.forEach { cell ->
            cell.visibility = View.INVISIBLE
        }
    }

    private fun updateChanceImage(){
        if (chance == 1){
            binding.ivChance.setImageResource(R.drawable.ic_x)
        }
        else{
            binding.ivChance.setImageResource(R.drawable.ic_o)
        }
    }

    private fun updateScoreText() {
        binding.tvNumberOfXWins.text = "$noOfXWins Wins"
        binding.tvNumberOfDraws.text = "$noOfDraws Draws"
        binding.tvNumberOfOWins.text = "$noOf0wins Wins"
    }

    private fun showSettingsDialog() {
        val dialog = CustomDialogSetting(this)
        dialog.show()
    }

    private fun checkWinner(){
        // Check rows
        for (i in 0 until 9 step 3){
            if (board[i] != defaultCellValue && board[i] == board[i + 1] && board[i] == board[i + 2]){
                showWinner(board[i])
                for (x in board.indices){
                    board[x] = 2
                }
                return
            }
        }

        // Check columns
        for (i in 0 until 3){
            if (board[i] != defaultCellValue && board[i] == board[i + 3] && board[i] == board[i + 6]){
                showWinner(board[i])
                for (x in board.indices){
                    board[x] = 2
                }
                return
            }
        }

        // Check diagonals
        if (board[0] != defaultCellValue && board[0] == board[4] && board[0] == board[8]){
            showWinner(board[0])
            for (x in board.indices){
                board[x] = 2
            }
            return
        }
        if (board[2] != defaultCellValue && board[2] == board[4] && board[2] == board[6]){
            showWinner(board[2])
            for (x in board.indices){
                board[x] = 2
            }
            return
        }

        // Check for a tie
        if (board.none { it == defaultCellValue }) {
            showDraw()
            for (x in board.indices){
                board[x] = 2
            }
            return
        }
    }

    private fun showWinner(player: Int) {
        if (player == 0) {
            noOf0wins++
        }
        else {
            noOfXWins++
            chance = 1 - chance
        }
        updateScoreText()
        handler.postDelayed({
            resetBoardUI()
        }, 300)
    }

    private fun showDraw() {
        noOfDraws++
        updateScoreText()
        handler.postDelayed({
            resetBoardUI()
        }, 300)
    }

    private fun updateBoardUI() {
        for (i in board.indices) {
            when (board[i]) {
                0 -> setOVisibility(i)
                1 -> setXVisibility(i)
                else -> clearCellVisibility(i)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        applyUIModeFromPreferences()
    }

    private fun applyUIModeFromPreferences() {
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getBoolean("isDarkMode", false)

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setOVisibility(index: Int) {
        when (index) {
            0 -> binding.cell1o.visibility = View.VISIBLE
            1 -> binding.cell2o.visibility = View.VISIBLE
            2 -> binding.cell3o.visibility = View.VISIBLE
            3 -> binding.cell4o.visibility = View.VISIBLE
            4 -> binding.cell5o.visibility = View.VISIBLE
            5 -> binding.cell6o.visibility = View.VISIBLE
            6 -> binding.cell7o.visibility = View.VISIBLE
            7 -> binding.cell8o.visibility = View.VISIBLE
            8 -> binding.cell9o.visibility = View.VISIBLE
        }
    }

    private fun setXVisibility(index: Int) {
        when (index) {
            0 -> binding.cell1x.visibility = View.VISIBLE
            1 -> binding.cell2x.visibility = View.VISIBLE
            2 -> binding.cell3x.visibility = View.VISIBLE
            3 -> binding.cell4x.visibility = View.VISIBLE
            4 -> binding.cell5x.visibility = View.VISIBLE
            5 -> binding.cell6x.visibility = View.VISIBLE
            6 -> binding.cell7x.visibility = View.VISIBLE
            7 -> binding.cell8x.visibility = View.VISIBLE
            8 -> binding.cell9x.visibility = View.VISIBLE
        }
    }

    private fun clearCellVisibility(index: Int) {
        when (index) {
            0 -> {
                binding.cell1x.visibility = View.INVISIBLE
                binding.cell1o.visibility = View.INVISIBLE
            }
            1 -> {
                binding.cell2x.visibility = View.INVISIBLE
                binding.cell2o.visibility = View.INVISIBLE
            }
            2 -> {
                binding.cell3x.visibility = View.INVISIBLE
                binding.cell3o.visibility = View.INVISIBLE
            }
            3 -> {
                binding.cell4x.visibility = View.INVISIBLE
                binding.cell4o.visibility = View.INVISIBLE
            }
            4 -> {
                binding.cell5x.visibility = View.INVISIBLE
                binding.cell5o.visibility = View.INVISIBLE
            }
            5 -> {
                binding.cell6x.visibility = View.INVISIBLE
                binding.cell6o.visibility = View.INVISIBLE
            }
            6 -> {
                binding.cell7x.visibility = View.INVISIBLE
                binding.cell7o.visibility = View.INVISIBLE
            }
            7 -> {
                binding.cell8x.visibility = View.INVISIBLE
                binding.cell8o.visibility = View.INVISIBLE
            }
            8 -> {
                binding.cell9x.visibility = View.INVISIBLE
                binding.cell9o.visibility = View.INVISIBLE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(boardKey, board)
        outState.putInt(chanceKey, chance)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreBoardState(savedInstanceState)
    }

    private fun restoreBoardState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val savedBoard = it.getIntArray(boardKey)
            savedBoard?.copyInto(board)
            chance = it.getInt(chanceKey)
            updateBoardUI()
        }
    }
}

