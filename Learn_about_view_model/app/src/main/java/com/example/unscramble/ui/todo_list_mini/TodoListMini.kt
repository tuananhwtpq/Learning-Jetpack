package com.example.unscramble.ui.todo_list_mini

import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.ui.theme.UnscrambleTheme
import com.example.unscramble.ui.todo_list_mini.viewmodel.TodoListFilter
import com.example.unscramble.ui.todo_list_mini.viewmodel.TodoViewModel

@Composable
fun TodoListMini(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel = viewModel()
) {

    val uiState by todoViewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = "Todo List Mini", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.inputMessage,
            onValueChange = {
                todoViewModel.updateInputMessage(it)
            },
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            label = {
                Text("Task Name")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = {
                todoViewModel.addTaskToList()
                keyboardController?.hide()
            }),
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.colors(

            )
        )

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage.toString(),
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(onClick = {
            todoViewModel.addTaskToList()
            keyboardController?.hide()
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.totalTaskDone != 0) {
            Text(
                text = "Total tasks done: ${uiState.totalTaskDone} / ${uiState.totalTask}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        if (uiState.totalTask != 0) {
            Text(
                text = "Total tasks: ${uiState.totalTask}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "Filter"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Button(onClick = {
                todoViewModel.updateFilter(TodoListFilter.ALL)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "All")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                todoViewModel.updateFilter(TodoListFilter.DONE)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Done")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                todoViewModel.updateFilter(TodoListFilter.UNDONE)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Undone")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = uiState.filteredTaskList,
                key = { it.id }
            ) {
                SingleTask(
                    taskName = it.taskName,
                    isChecked = it.isDone,
                    onCheckedChange = { checked ->
                        todoViewModel.updateCheckedChange(it.id, checked)
                    },
                    onDeleteClick = {
                        todoViewModel.deleteTask(it.id)
                    },
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        if (uiState.filteredTaskList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = if (uiState.taskList.isEmpty()) "Empty tasks" else "No tasks match this filter",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }


    }
}

@Composable
fun SingleTask(
    taskName: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                onCheckedChange(checked)
            },
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = taskName, modifier = Modifier.weight(1f))
        Button(onClick = {
            onDeleteClick()
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
