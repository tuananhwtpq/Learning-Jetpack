package com.example.unscramble.ui.expense_tracker_mini.data

sealed interface ExpenseUiEvent {
    data class EnterTitle(val title: String) : ExpenseUiEvent
    data class EnterAmount(val amount: String) : ExpenseUiEvent
    data class DeleteExpenseItem(val itemId: Int) : ExpenseUiEvent
    object AddExpense : ExpenseUiEvent
//    data class FilterList(val filter: ExpenseFilter) : ExpenseUiEvent
}