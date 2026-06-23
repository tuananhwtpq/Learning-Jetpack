package com.example.unscramble.ui.quizz_app_mini

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.ui.quizz_app_mini.data.Question
import com.example.unscramble.ui.quizz_app_mini.data.QuizUiEvent
import com.example.unscramble.ui.quizz_app_mini.data.QuizUiState
import com.example.unscramble.ui.quizz_app_mini.data.questions
import com.example.unscramble.ui.quizz_app_mini.viewmodel.QuizViewModel
import com.example.unscramble.ui.theme.UnscrambleTheme


@Composable
fun QuizAppRoute(
    quizViewModel: QuizViewModel = viewModel()
) {

    val uiState by quizViewModel.uiState.collectAsState()

    QuizAppScreen(
        uiState = uiState,
        onEvent = quizViewModel::onEvent
    )
}

@Composable
fun QuizAppScreen(
    uiState: QuizUiState,
    onEvent: (QuizUiEvent) -> Unit
) {
    val currentQuestionData = uiState.currentQuestionData ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(20.dp)
    ) {
        Text(
            text = "Welcome to the Quiz App!",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Question: ${uiState.currentQuestion + 1} / ${questions.size}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = currentQuestionData.question,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in currentQuestionData.listAnswer.indices) {
                SingleAnswer(
                    answer = currentQuestionData.listAnswer[i],
                    onClick = {
                        onEvent(QuizUiEvent.ChoseAnswer(i))
                    },
                    isSelected = uiState.currentUserAnswerIndex == i
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.errorMessage.isEmpty().not()) {
            Text(
                text = uiState.errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = "Please choose one answer",
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onEvent(QuizUiEvent.Submit)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color(0xFF6200EE)
            )
        ) {
            Text(
                text = "Submit",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.isQuizFinish) {
            Text(
                text = "You have just finish this game!",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "Score: ${uiState.score}",
                color = Color.Black,
            )
        }
    }

    if (uiState.isQuizFinish) {
        FinishGameDialog {
            onEvent(QuizUiEvent.PlayAgain)
        }
    }
}

@Composable
fun SingleAnswer(
    answer: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {

    TextButton(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFabcabc) else Color(0xFFE0E0E0),
            contentColor = if (isSelected) Color.White else Color.Green,
        )
    ) {
        Text(
            text = answer,
            color = if (isSelected) Color.White else Color.Black,
            fontWeight = FontWeight.Bold,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishGameDialog(
    onEvent: () -> Unit
) {
    Dialog(
        onDismissRequest = {

        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Congratulations! You have finished the quiz.",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {

                        }
                    ) {
                        Text(
                            text = "Dismiss",
                        )
                    }

                    Button(
                        onClick = {
                            onEvent()
                        }
                    ) {
                        Text(
                            text = "Play Again"
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FinishDialogPreview() {
    UnscrambleTheme {
        FinishGameDialog(
            onEvent = {}
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun QuizAppScreenPreview() {
    UnscrambleTheme {
        QuizAppScreen(
            uiState = QuizUiState(
                currentQuestion = 1,
                currentQuestionData = Question(
                    id = 2,
                    question = "Jetpack Compose is used to build?",
                    listAnswer = listOf("UI", "Database", "Network layer"),
                    correctAnswer = "UI"
                ),
                score = 20,
                currentUserAnswerIndex = 1,
                errorMessage = "",
                isQuizFinish = true
            ),
            onEvent = {}
        )
    }
}
