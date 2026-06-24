package com.example.unscramble.ui.expense_tracker_mini.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseItem
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseUiEvent
import com.example.unscramble.ui.expense_tracker_mini.data.ExpenseUiState
import com.example.unscramble.ui.expense_tracker_mini.viewmodel.ExpenseViewModel
import com.example.unscramble.ui.theme.UnscrambleTheme


@Composable
fun ExpenseMiniRoute(
    expenseViewModel: ExpenseViewModel = viewModel()
) {
    val uiState by expenseViewModel.uiState.collectAsState()
    ExpenseMiniScreen(
        uiState = uiState,
        onEvent = expenseViewModel::onEvent
    )
}

@Composable
fun ExpenseMiniScreen(
    uiState: ExpenseUiState,
    onEvent: (ExpenseUiEvent) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Expense Tracker Mini",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            //title
            SingleOutlineTextField(
                value = uiState.inputTitle,
                onValueChange = {
                    onEvent(ExpenseUiEvent.EnterTitle(it))
                },
                label = "Expense title...",
                placeholder = "Enter expense title",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onDoneClick = {},
                onNextClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            //amout
            SingleOutlineTextField(
                value = uiState.inputAmount,
                onValueChange = {
                    onEvent(ExpenseUiEvent.EnterAmount(it))
                },
                label = "Amount...",
                placeholder = "Enter amount in numbers",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                onDoneClick = {
                    onEvent(ExpenseUiEvent.AddExpense)
                    keyboardController?.hide()
                },
                onNextClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (uiState.errorMessage.isNotBlank()) {
                Text(text = uiState.errorMessage, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onEvent(ExpenseUiEvent.AddExpense)
                    keyboardController?.hide()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFabcabc)
                )
            ) {
                Text(
                    text = "Add Expense",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
//
//                for (i in 0 until ExpenseFilter.entries.size) {
//                    ButtonFilter(
//                        title = ExpenseFilter.entries[i].name,
//                        onButtonClick = {
//
//                        }
//                    )
//                }
//            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total Expenses: ${uiState.totalAmount}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            //empty State
            if (uiState.expenseList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Empty List!",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                items(
                    items = uiState.expenseList
                ) {
                    SingleExpenseItem(
                        item = it,
                        onDeleteClick = {
                            onEvent(ExpenseUiEvent.DeleteExpenseItem(it.id))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonFilter(
    modifier: Modifier = Modifier,
    title: String,
    onButtonClick: () -> Unit
) {
    Button(onClick = {
        onButtonClick()
    }) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun SingleOutlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onDoneClick: (() -> Unit)? = null,
    onNextClick: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge, color = Color.Gray
            )
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNextClick?.invoke()
            },
            onDone = {
                onDoneClick?.invoke()
            }
        )
    )
}

@Composable
fun SingleExpenseItem(
    modifier: Modifier = Modifier,
    item: ExpenseItem,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = item.expenseTitle,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.weight(1f))
//
//        Text(
//            text = item.type.toString(),
//            style = MaterialTheme.typography.bodyLarge
//        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.amount.toString(),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.width(16.dp))

        TextButton(
            onClick = {
                onDeleteClick()
            }
        ) {
            Text(
                text = "X",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ExpenseMiniPreview() {
    UnscrambleTheme {
        ExpenseMiniScreen(
            uiState = ExpenseUiState(
                inputTitle = "",
                inputAmount = "",
                expenseList = listOf(
                    ExpenseItem(
                        id = 1,
                        expenseTitle = "Lunch",
                        amount = 50000,
//                        type = ExpenseFilter.FOOD
                    ),
                    ExpenseItem(
                        id = 2,
                        expenseTitle = "Dinner",
                        amount = 75000,
//                        type = ExpenseFilter.TRAVEL
                    ),
                ),
                errorMessage = ""
            ),
            onEvent = {}
        )
    }
}