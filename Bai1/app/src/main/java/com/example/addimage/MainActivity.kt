package com.example.addimage

import android.graphics.Paint
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addimage.ui.theme.AddImageTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddImageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {

                    }
                }
            }
        }
    }
}

@Composable
fun ThirdEx() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Rows(
            modifier = Modifier.weight(1f),
            text1 = "Text composable",
            content1 = "Displays text and follows the recommended Material Design guidelines.",
            color1 = Color(0xFFEADDFF),
            text2 = "Image composable",
            content2 = "Creates a composable that lays out and draws a given Painter class object.",
            color2 = Color(0xFFD0BCFF)
        )
        Rows(
            modifier = Modifier.weight(1f),
            text1 = "Text composable",
            content1 = "Displays text and follows the recommended Material Design guidelines.",
            color1 = Color(0xFFEADDFF),
            text2 = "Image composable",
            content2 = "Creates a composable that lays out and draws a given Painter class object.",
            color2 = Color(0xFFD0BCFF)
        )
    }

}

@Composable
fun Rows(
    modifier: Modifier = Modifier,
    text1: String,
    content1: String,
    color1: Color,
    text2: String,
    content2: String,
    color2: Color
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        InfoCard(
            text = text1,
            content = content1,
            color = color1,
            modifier = Modifier.weight(1f)
        )
        InfoCard(
            text = text2,
            content = content2,
            color = color2,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    text: String,
    content: String,
    color: Color
) {
    Column(
        modifier = modifier
            .background(color = color).fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally),

            )

        Text(
            text = content,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp),
            fontStyle = FontStyle.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AddImageTheme {
        ThirdEx()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AddImageTheme {
        InfoCard(
            modifier = Modifier,
            text = "Title",
            content = "Creates a composable that lays out and draws a given Painter class object.",
            color = Color.Green
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    AddImageTheme {
        Rows(
            text1 = "Text composable",
            content1 = "Displays text and follows the recommended Material Design guidelines.",
            color1 = Color(0xFFEADDFF),
            text2 = "Image composable",
            content2 = "Creates a composable that lays out and draws a given Painter class object.",
            color2 = Color(0xFFD0BCFF)
        )
    }
}