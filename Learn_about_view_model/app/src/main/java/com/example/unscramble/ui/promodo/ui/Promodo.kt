package com.example.unscramble.ui.promodo.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.ui.promodo.data.PromodoUiEvent
import com.example.unscramble.ui.promodo.data.PromodoUiState
import com.example.unscramble.ui.promodo.viewmodel.PromodoViewModel
import com.example.unscramble.ui.theme.UnscrambleTheme


@Composable
fun PromodoRoute(promodoViewModel: PromodoViewModel = viewModel()) {

    val uiState by promodoViewModel.uiState.collectAsState()
    PromodoScreen(
        uiState = uiState,
        onEvent = promodoViewModel::onEvent
    )
}


@Composable
fun PromodoScreen(
    modifier: Modifier = Modifier,
    uiState: PromodoUiState,
    onEvent: (PromodoUiEvent) -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Pomodoro Timer",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = timerToString(uiState.timeLeftInSeconds),
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onEvent(PromodoUiEvent.StartTimer)
                    },
                    enabled = !uiState.isRunning && uiState.timeLeftInSeconds > 0,
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Start", style = MaterialTheme.typography.bodyLarge)
                }

                Button(
                    onClick = {
                        onEvent(PromodoUiEvent.PauseTimer)
                    },
                    enabled = uiState.isRunning,
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Pause", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = modifier.height(30.dp))

            Button(onClick = {
                onEvent(PromodoUiEvent.ResetTimer)
            }, shape = RoundedCornerShape(32.dp)) {
                Text(text = "Reset", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

    if (uiState.isFinished) {
        Box(
            modifier = modifier
                .systemBarsPadding()
                .fillMaxSize()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "Session finished",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }

}

fun timerToString(timer: Int): String {
    val seconds = timer % 60
    val minutes = timer / 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PromodoPreview() {
    UnscrambleTheme {
        PromodoScreen(
            uiState = PromodoUiState(
                isFinished = true,
                timeLeftInSeconds = 135
            ),
            onEvent = {}
        )
    }
}
