package com.example.unscramble.ui.counter_advanced

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CounterUIState())
    val uiState: StateFlow<CounterUIState> = _uiState.asStateFlow()

    fun onEvent(event: CounterUiEvent) {
        when (event) {
            CounterUiEvent.Decrement -> decreaseCount()
            CounterUiEvent.Increment -> increaseCount()
            CounterUiEvent.Reset -> resetCount()
        }
    }

    private fun decreaseCount() {
        val nextValue = uiState.value.count - 1
        if (nextValue < 0) {
            _uiState.update { currentState ->
                currentState.copy(
                    isShowWarning = true,
                    messageWarning = "Counter cannot be less than 0"
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    count = currentState.count - 1,
                    isShowWarning = false
                )
            }
        }
    }

    private fun increaseCount() {
        val nextValue = uiState.value.count + 1
        if (nextValue > 10) {
            _uiState.update { currentState ->
                currentState.copy(
                    isShowWarning = true,
                    messageWarning = "Counter cannot be greater than 10"
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    count = currentState.count + 1,
                    isShowWarning = false
                )
            }
        }
    }

    private fun resetCount() {
        _uiState.value = CounterUIState(
            count = 0,
            isShowWarning = false,
            messageWarning = ""
        )
    }

}