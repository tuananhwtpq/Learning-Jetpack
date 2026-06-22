package com.example.unscramble.ui.counter_advanced

sealed interface CounterUiEvent {
    object Increment : CounterUiEvent
    object Decrement : CounterUiEvent
    object Reset : CounterUiEvent
}
