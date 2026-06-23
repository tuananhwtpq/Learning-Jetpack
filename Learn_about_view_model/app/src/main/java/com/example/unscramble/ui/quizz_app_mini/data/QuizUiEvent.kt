package com.example.unscramble.ui.quizz_app_mini.data

sealed interface QuizUiEvent {
    data class ChoseAnswer(val answerIndex: Int) : QuizUiEvent
    object Submit : QuizUiEvent
    object PlayAgain : QuizUiEvent
}