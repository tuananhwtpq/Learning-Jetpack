package com.example.art_space

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_space.ui.theme.Art_SpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Art_SpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        ArtSpace()
                    }
                }
            }
        }
    }
}


@Composable
fun ArtSpace(modifier: Modifier = Modifier) {

//    val imageResouce = listOf<Int>(
//        R.drawable.bg_light,
//    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //region IMAGE
        Card(
            modifier = modifier
                .background(Color.White)
                .padding(12.dp)
                .width(300.dp)
                .height(450.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.bg_light),
                contentDescription = "Image description",
                modifier = modifier
                    .fillMaxSize()
                    .padding(36.dp),
                alignment = Alignment.Center
            )
        }

        Spacer(modifier = modifier.height(12.dp))

        //region AUTHOR INFO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(Color.LightGray)
                .padding(12.dp),

            ) {
            Text(
                text = "Still Live in Earth",
                fontStyle = FontStyle.Normal,
                modifier = modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )

            Spacer(modifier = modifier.height(4.dp))

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Owner Scoot",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(end = 12.dp)
                )
                Text(
                    text = "(2023)"
                )
            }


        }

        //region BUTTON

        Spacer(
            modifier = modifier.size(12.dp)
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(
                text = "Previous",
                onButtonClick = { },
                modifier = modifier
            )

            CustomButton(
                text = "Next",
                onButtonClick = {},
                modifier = modifier
            )
        }

    }
}

@Composable
fun CustomButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    TextButton(
        onClick = onButtonClick,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.LightGray,
            contentColor = Color.White
        ),
        modifier = modifier.width(120.dp)
    ) {
        Text(
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Art_SpaceTheme {
        CustomButton(
            text = "Tun Tun",
            onButtonClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Art_SpaceTheme {
        ArtSpace()
    }
}