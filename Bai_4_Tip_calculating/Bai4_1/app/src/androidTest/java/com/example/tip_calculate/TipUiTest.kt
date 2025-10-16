package com.example.tip_calculate

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tip_calculate.ui.theme.Tip_CalculateTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

//class TipUiTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun calculate_20_percent_tip() {
//        composeTestRule.setContent {
//            Tip_CalculateTheme {
//                TipTimeLayout()
//            }
//        }
//
//        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
//        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
//
//        val expectTip = NumberFormat.getCurrencyInstance().format(2)
//        composeTestRule.onNodeWithText("Tip Amount: $expectTip").assertExists(
//            "No node with this text was found."
//        )
//    }
//}