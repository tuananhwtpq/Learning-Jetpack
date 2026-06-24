package com.example.unscramble.ui.expense_tracker_mini.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseFilter
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseItem
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseUiEvent
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ExpenseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState: StateFlow<ExpenseUiState> = _uiState.asStateFlow()

    private var clearMessageJob: Job? = null

    private fun showTempMessage(message: String) {
        clearMessageJob?.cancel()

        _uiState.update { currentState ->
            currentState.copy(errorMessage = message)
        }

        clearMessageJob = viewModelScope.launch {
            delay(2000L)
            _uiState.update { currentState ->
                currentState.copy(
                    errorMessage = ""
                )
            }
        }
    }

    fun onEvent(event: ExpenseUiEvent) {
        when (event) {
            is ExpenseUiEvent.EnterAmount -> {
                updateInputAmount(event.amount)
            }

            is ExpenseUiEvent.EnterTitle -> {
                updateInputTitle(event.title)
            }

            is ExpenseUiEvent.DeleteExpenseItem -> deleteItemFromList(event.itemId)
            ExpenseUiEvent.AddExpense -> {
                addItemToList()
            }
//            is ExpenseUiEvent.FilterList -> updateListByFilter(event.filter)
        }
    }

    private fun updateListByFilter(filter: ExpenseFilter) {

    }

    private fun addItemToList() {

        val title = uiState.value.inputTitle.trim()
        val amount = uiState.value.inputAmount.toIntOrNull()

        if (title.isEmpty() || amount == null || amount <= 0) {
            showTempMessage("Please enter a valid value")
            return
        }

        val newItem = ExpenseItem(
            id = UUID.randomUUID().hashCode(),
            expenseTitle = title,
            amount = amount,
//            type = uiState.value.filterChosen
        )

        _uiState.update { currentState ->
            currentState.copy(
                inputTitle = "",
                inputAmount = "",
                expenseList = currentState.expenseList + newItem,
                errorMessage = ""
            )
        }
    }

    private fun deleteItemFromList(itemId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                expenseList = currentState.expenseList.filterNot { it.id == itemId },
            )
        }

        showTempMessage("Delete success")

    }

    private fun updateInputTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inputTitle = title
            )
        }
    }

    private fun updateInputAmount(amount: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inputAmount = amount
            )
        }
    }
}