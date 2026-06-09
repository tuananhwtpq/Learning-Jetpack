package com.example.unscramble.ui.todo_list_mini.data

data class Task(
    val id: Int,
    val taskName: String,
    var isDone: Boolean = false
)
