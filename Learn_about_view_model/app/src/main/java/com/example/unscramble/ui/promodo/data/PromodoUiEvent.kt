package com.example.unscramble.ui.promodo.data

sealed interface PromodoUiEvent {
    object StartTimer : PromodoUiEvent
    object PauseTimer : PromodoUiEvent
    object ResetTimer : PromodoUiEvent
}