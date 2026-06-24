package com.example.unscramble.ui.promodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unscramble.ui.promodo.data.PromodoUiEvent
import com.example.unscramble.ui.promodo.data.PromodoUiState
import com.example.unscramble.ui.promodo.data.START_TIMER
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PromodoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PromodoUiState())
    val uiState: StateFlow<PromodoUiState> = _uiState.asStateFlow()

    private var startTimerJob: Job? = null

    fun onEvent(event: PromodoUiEvent) {
        when (event) {
            PromodoUiEvent.PauseTimer -> pauseTimer()
            PromodoUiEvent.ResetTimer -> resetTimer()
            PromodoUiEvent.StartTimer -> startTimer()
        }
    }

    private fun startTimer() {
        if (uiState.value.isRunning || uiState.value.timeLeftInSeconds <= 0) {
            return
        }

        startTimerJob?.cancel()
        _uiState.update { currentState ->
            currentState.copy(
                isRunning = true,
                isFinished = false
            )
        }

        startTimerJob = viewModelScope.launch {
            while (_uiState.value.timeLeftInSeconds > 0) {
                delay(1000L)

                _uiState.update { currentState ->
                    currentState.copy(
                        timeLeftInSeconds = currentState.timeLeftInSeconds - 1
                    )
                }
            }

            _uiState.update { currentState ->
                currentState.copy(
                    isRunning = false,
                    isFinished = true,
                )
            }
        }
    }

    private fun pauseTimer() {
        if (uiState.value.isRunning) {
            startTimerJob?.cancel()

            _uiState.update { currentState ->
                currentState.copy(isRunning = false)
            }
        }

    }

    private fun resetTimer() {
        startTimerJob?.cancel()

        _uiState.update { currentState ->
            currentState.copy(
                timeLeftInSeconds = START_TIMER,
                isRunning = false,
                isFinished = false
            )
        }
    }


}
