package com.example.unscramble.ui.quizz_app_mini.data

data class Question(
    val id: Int,
    val question: String,
    val listAnswer: List<String>,
    val correctAnswer: String,
)
