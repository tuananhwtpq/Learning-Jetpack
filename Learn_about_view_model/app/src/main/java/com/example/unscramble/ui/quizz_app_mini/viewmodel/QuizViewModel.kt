package com.example.unscramble.ui.quizz_app_mini.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unscramble.ui.quizz_app_mini.data.INCREASE_SCORE
import com.example.unscramble.ui.quizz_app_mini.data.QuizUiEvent
import com.example.unscramble.ui.quizz_app_mini.data.QuizUiState
import com.example.unscramble.ui.quizz_app_mini.data.questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(
        QuizUiState(
            currentQuestionData = questions.firstOrNull()
        )
    )
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun onEvent(event: QuizUiEvent) {
        when (event) {
            is QuizUiEvent.ChoseAnswer -> {
                updateUserAnswer(event.answerIndex)
            }

            QuizUiEvent.Submit -> submitAnswer()
            QuizUiEvent.PlayAgain -> resetGame()
        }
    }

    private fun submitAnswer() {
        val currentState = uiState.value
        val currentQuestionData = currentState.currentQuestionData ?: return

        if (currentState.currentUserAnswerIndex == -1) {
            _uiState.update { state ->
                state.copy(
                    errorMessage = "Please choose one answer"
                )
            }
            return
        }

        val rightAnswer = currentQuestionData.correctAnswer
        val userAnswer = currentQuestionData.listAnswer[currentState.currentUserAnswerIndex]
        val isCorrect = rightAnswer == userAnswer

        if (currentState.currentQuestion < questions.size - 1) {
            val nextQuestionIndex = currentState.currentQuestion + 1
            _uiState.update { state ->
                state.copy(
                    currentQuestion = nextQuestionIndex,
                    currentQuestionData = questions[nextQuestionIndex],
                    currentUserAnswerIndex = -1,
                    score = if (isCorrect) state.score + INCREASE_SCORE else state.score,
                    errorMessage = if (isCorrect) "" else "Wrong Answer"
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    score = if (isCorrect) state.score + INCREASE_SCORE else state.score,
                    errorMessage = if (isCorrect) "" else "Wrong Answer",
                    isQuizFinish = true
                )
            }
        }
    }

    private fun updateUserAnswer(currentAnswerIndex: Int) {

        _uiState.update { currentState ->
            currentState.copy(
                currentUserAnswerIndex = currentAnswerIndex,
                errorMessage = ""
            )
        }
    }

    private fun resetGame() {
        _uiState.value = QuizUiState(
            currentQuestionData = questions.firstOrNull(),
            currentQuestion = 0,
            currentUserAnswerIndex = -1,
            errorMessage = "",
            isQuizFinish = false,
            score = 0
        )
    }

}
