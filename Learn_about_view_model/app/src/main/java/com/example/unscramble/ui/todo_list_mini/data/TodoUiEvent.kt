package com.example.unscramble.ui.todo_list_mini.data

import com.example.unscramble.ui.todo_list_mini.viewmodel.TodoListFilter

sealed interface TodoUiEvent {
    data class UpdateUserInput(val input: String) : TodoUiEvent
    object AddTask : TodoUiEvent
    data class UpdateFilter(val filter: TodoListFilter) : TodoUiEvent
    data class UpdateCheckedChange(val taskId: Int, val isChecked: Boolean) : TodoUiEvent
    data class DeleteTask(val taskId: Int) : TodoUiEvent
}