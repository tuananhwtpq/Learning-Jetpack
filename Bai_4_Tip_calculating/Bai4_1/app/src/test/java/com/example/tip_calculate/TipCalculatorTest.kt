package com.example.tip_calculate

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTest {

    @Test
    fun calculateTip_20PercentNoRoundup(){
        val amout = 10.0
        val tip = 20.0
        val expectTip = NumberFormat.getCurrencyInstance().format(2)
        val currentTip = calculateTip(amount =  amout, tipPercent = tip, roundUp = false)
        assertEquals(expectTip, currentTip)

    }
}