package com.example.lemon_tree

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.example.lemon_tree.ui.theme.Lemon_TreeTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lemon_TreeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonTree(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonTree(modifier: Modifier = Modifier) {

    var listOfString = listOf<String>(
        stringResource(R.string.start_lemon),
        stringResource(R.string.tapping_lemon),
        stringResource(R.string.drink_lemon),
        stringResource(R.string.tap_empty_glass),
    )

    var listOfImage = listOf<Int>(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart,
    )

    var currentChoice by remember { mutableStateOf(0) }

    val resultString = listOfString[currentChoice]
    val resultImage = listOfImage[currentChoice]
    val squeezeNumber = (2..4).random()
    var squeezeNumbers by rememberSaveable { mutableStateOf(squeezeNumber) }
    var currentCount by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(300.dp)
                .clip(CircleShape)
                .clickable(
                    onClick = {
                        if (currentChoice >= 3) {
                            currentChoice = 0
                        } else if (currentChoice == 1) {
                            if (currentCount < squeezeNumbers) {
                                currentCount++
                            } else {
                                currentChoice += 1
                                currentCount = 0
                                squeezeNumbers = (2..4).random()
                            }
                        } else {
                            currentChoice += 1

                        }
                        Log.d("LemonTree", "currentChoice: $currentChoice")
                        Log.d("LemonTree", "resultString: $resultString")
                        Log.d("LemonTree", "resultImage: $resultImage")
                        Log.d("LemonTree", "squeezeNumbers: $squeezeNumbers")
                        Log.d("LemonTree", "currentCount: $currentCount")
                        Log.d("LemonTree", "----------------------------")
                    }
                )
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .background(Color.Green),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(resultImage),
                contentDescription = "Lemon Tree"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = resultString,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lemon_TreeTheme {
        LemonTree()
    }
}