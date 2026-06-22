package com.example.unscramble.ui.counter_advanced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.ui.theme.UnscrambleTheme


@Composable
fun CounterAdvancedScreen(
    counterViewModel: CounterViewModel = viewModel()
) {
    val uiState by counterViewModel.uiState.collectAsState()

    CounterAdvanced(
        uiState = uiState,
        onEvent = counterViewModel::onEvent,
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun CounterAdvanced(
    uiState: CounterUIState,
    onEvent: (CounterUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // BUtton Decrease
            Button(onClick = {
                onEvent(CounterUiEvent.Decrement)
            }) {
                Text(text = "-", fontSize = 24.sp)
            }

            Text(
                text = "Counter: ${uiState.count}",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 24.sp
            )

            //Button increase
            Button(onClick = {
                onEvent(CounterUiEvent.Increment)
            }) {
                Text(text = "+", fontSize = 24.sp)
            }
        }

        Button(onClick = {
            onEvent(CounterUiEvent.Reset)
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Reset")
        }

        Text(
            text = if (uiState.isShowWarning) uiState.messageWarning else "",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CounterAdvancedPreview() {
    UnscrambleTheme {
    }
}