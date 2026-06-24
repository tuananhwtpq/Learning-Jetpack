package com.example.unscramble.ui.promodo.data

data class PromodoUiState(
    val timeLeftInSeconds: Int = START_TIMER, // 25 minutes
    val isRunning: Boolean = false,
    val isFinished: Boolean = false
)

const val START_TIMER = 135
