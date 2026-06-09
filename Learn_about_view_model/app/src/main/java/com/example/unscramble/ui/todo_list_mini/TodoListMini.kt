package com.example.unscramble.ui.todo_list_mini

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unscramble.ui.theme.UnscrambleTheme

@Composable
fun TodoListMini(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = "Todo List Mini", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            label = {
                Text("Task Name")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {

            })
        )

        Text(
            text = "",
            textAlign = TextAlign.Center,

            )

        Button(onClick = {

        }) {
            Text(text = "Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(10) {
                SingleTask(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        }

        Text(
            text = "Empty tasks", modifier = Modifier.align(Alignment.CenterHorizontally),
        )

    }
}

@Composable
fun SingleTask(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = {

            },
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Task Name", modifier = Modifier.weight(1f))
        Button(onClick = {

        }) {
            Text(text = "Delete")
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TodoListMiniPreview() {
    UnscrambleTheme {
        TodoListMini()
    }
}