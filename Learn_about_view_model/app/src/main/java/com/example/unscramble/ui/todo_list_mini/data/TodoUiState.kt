package com.example.unscramble.ui.todo_list_mini.data

import com.example.unscramble.ui.todo_list_mini.viewmodel.TodoListFilter

data class TodoUiState(
    val inputMessage: String = "",
    val taskList: List<Task> = emptyList(),
    val filteredTaskList: List<Task> = emptyList(),
    val errorMessage: String? = null,
    val totalTaskDone: Int = 0,
    val totalTask: Int = 0,
    val currentFilter: TodoListFilter = TodoListFilter.ALL
)
