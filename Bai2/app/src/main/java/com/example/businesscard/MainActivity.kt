package com.example.businesscard

import android.os.Bundle
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F4EA))
            .padding(bottom = 32.dp)
    ) {
        TopPanel(
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItemInfo(drawable = R.drawable.ic_launcher_foreground, content = "123456789")
            ItemInfo(drawable = R.drawable.ic_launcher_foreground, content = "123456789")
            ItemInfo(drawable = R.drawable.ic_launcher_foreground, content = "123456789")

        }
    }

}

@Composable
fun TopPanel(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.background(Color.Gray)
        ) {
            Image(
                painter = painterResource(R.drawable.android_logo),
                contentDescription = "",
                Modifier.size(100.dp)
            )
        }

        Text(
            text = "Tran Tuan Anh",
            fontSize = 36.sp,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Android Developer",
            fontSize = 24.sp,
            color = Color.Green,
            modifier = Modifier.padding(8.dp)
        )

    }

}

@Composable
fun ItemInfo(modifier: Modifier = Modifier, drawable: Int, content: String) {

    Row(
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Icon(
            painter = painterResource(drawable),
            contentDescription = "",
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = content,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BusinessCardTheme {
        ItemInfo(
            modifier = Modifier,
            drawable = R.drawable.ic_launcher_foreground,
            content = "123456789"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    BusinessCardTheme {
        TopPanel()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        MainScreen()
    }
}