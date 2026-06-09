package com.example.unscramble.ui.counter_advanced

data class CounterUIState(
    val count: Int = 0,
    val isShowWarning: Boolean = false,
    val messageWarning: String = ""
)