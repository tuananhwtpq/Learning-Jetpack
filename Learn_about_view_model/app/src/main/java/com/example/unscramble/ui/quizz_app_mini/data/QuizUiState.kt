package com.example.unscramble.ui.quizz_app_mini.data

data class QuizUiState(
    val currentQuestion: Int = 0,
    val currentQuestionData: Question? = null,
    val score: Int = 0,
    val currentUserAnswerIndex: Int = -1,
    val errorMessage: String = "",
    val isQuizFinish: Boolean = false
)
