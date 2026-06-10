package com.example.unscramble.ui.todo_list_mini.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unscramble.ui.todo_list_mini.data.Task
import com.example.unscramble.ui.todo_list_mini.data.TodoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class TodoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    fun updateInputMessage(message: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inputMessage = message,
                errorMessage = null
            )
        }
    }

    fun addTaskToList() {
        if (uiState.value.inputMessage.isBlank()) {
            _uiState.update { currentState ->
                currentState.copy(
                    errorMessage = "Task name cannot be empty"
                )
            }
        } else {
            _uiState.update { currentState ->
                val newTask = Task(
                    id = UUID.randomUUID().hashCode(),
                    taskName = currentState.inputMessage,
                    isDone = false
                )
                val updatedList = currentState.taskList + newTask

                currentState.copy(
                    taskList = updatedList,
                    filteredTaskList = getListByFilter(updatedList, currentState.currentFilter),
                    inputMessage = "",
                    errorMessage = null,
                    totalTask = updatedList.size
                )
            }
        }
    }


    fun updateCheckedChange(taskId: Int, isChecked: Boolean) {
        _uiState.update { currentState ->
            val updatedList = currentState.taskList.map { task ->
                if (task.id == taskId) task.copy(isDone = isChecked) else task
            }

            currentState.copy(
                taskList = updatedList,
                filteredTaskList = getListByFilter(updatedList, currentState.currentFilter),
                totalTaskDone = updatedList.count { it.isDone }
            )
        }
    }

    fun deleteTask(taskId: Int) {
        _uiState.update { currentState ->
            val updatedList = currentState.taskList.filterNot { it.id == taskId }

            currentState.copy(
                taskList = updatedList,
                filteredTaskList = getListByFilter(updatedList, currentState.currentFilter),
                totalTask = updatedList.size,
                totalTaskDone = updatedList.count { it.isDone }
            )
        }
    }

    fun updateFilter(typeFilter: TodoListFilter) {
        _uiState.update { currentState ->
            currentState.copy(
                currentFilter = typeFilter,
                filteredTaskList = getListByFilter(currentState.taskList, typeFilter)
            )
        }
    }

    private fun getListByFilter(taskList: List<Task>, typeFilter: TodoListFilter): List<Task> {
        return when (typeFilter) {
            TodoListFilter.ALL -> taskList
            TodoListFilter.DONE -> taskList.filter { it.isDone }
            TodoListFilter.UNDONE -> taskList.filterNot { it.isDone }
        }
    }

}

enum class TodoListFilter {
    ALL, DONE, UNDONE
}
