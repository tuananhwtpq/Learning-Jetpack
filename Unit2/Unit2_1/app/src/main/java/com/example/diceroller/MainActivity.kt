package com.example.diceroller

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        DiceRollerApp()
                    }
                }
            }
        }
    }
}

/**
 * Hàm dùng để lăn xúc xắc
 *
 * Không để lần lăn tiếp theo trung với laanf lăn phía trước
 */

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {

    var result by remember { mutableStateOf(1) }

    var currentResult by remember { mutableStateOf(result) }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(imageResource),
            contentDescription = "Dice roller"
        )

        Button(onClick = {

            /**
             * Cách1:  này dùng hàm do while
             * Vì lúc nào cx sẽ cần lawn xúc xắc nên dùng do while khá hợp lý
             * lặp cho đến khi newResult != result
             * sau đó gán result = newResult
             */
//            var newResult: Int
//            do {
//                newResult = (1..6).random()
//            } while (newResult == result)
//            result = newResult


            /**
             * Cách 2: Tạo ra 1 biến currentResult để theo dõi biễn hiện tại
             * Nếu currentResult == result thì lăn lại xúc xắc
             * Sau khi đã khác biệt -> Thoát khỏi vòng lặp thì gán currentResult = result
             */

//            result = (1..6).random()
//            while (result == currentResult) {
//                result = (1..6).random()
//            }
//            currentResult = result

            /**
             * Cách 3: Dùng hàm filter để lọc
             * Tạo ra 1 danh sách kết quả có thể xảy ra
             * Sau đó dùng hàm lọc để lọc danh sách đó
             * Danh sách đó ko được chứa kết quả của result hiện tại
             * Sau đó lấy ra 1 phần tử ngẫu nhiên trong danh sách đó gà gán cho result
             */

//            val allFace = 1..6
//            val posibleResult = allFace.filter { it != result }
//            result = posibleResult.random()

            /**
             * Cách 4: Dùng generateSequence để tạo ra 1 chuỗi ngẫu nhiên
             * Tạo ra 1 chuỗi vô hạn lần tung xúc xắc, và lấy ra kết quar đầu tiên không trùng vowis result
             */

            result = generateSequence { (1..6).random() }.first { it != result }

        }) {
            Text(
                text = "ROLL",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceRollerTheme {

        Box(modifier = Modifier.fillMaxSize()) {
            DiceWithButtonAndImage()
        }
    }
}