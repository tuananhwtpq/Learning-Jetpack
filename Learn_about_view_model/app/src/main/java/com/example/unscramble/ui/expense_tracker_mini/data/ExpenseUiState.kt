package com.example.unscramble.ui.expense_tracker_mini.data

data class ExpenseUiState(
    val inputTitle: String = "",
    val inputAmount: String = "",
    val expenseList: List<ExpenseItem> = emptyList(),
    val errorMessage: String = "",
//    val filterChosen: ExpenseFilter = ExpenseFilter.FOOD,
) {
    val totalAmount get() = expenseList.sumOf { it.amount }
//    val filterList get() = expenseList.filter { it.type == filterChosen }
}

