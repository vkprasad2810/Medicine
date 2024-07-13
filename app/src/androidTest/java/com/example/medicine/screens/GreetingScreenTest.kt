package com.example.medicine.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.medicine.database.Medicine
import org.junit.Rule
import org.junit.Test

class GreetingScreenTest {


    @get:Rule
    val rule = createComposeRule()
    @Test
    fun setGreetingScreenTest() {
        val medicine = Medicine(1,"asprin", "1 dose", "500mg","Diabetes")

        rule.setContent {
            MedicineDetailScreen(medicine = medicine)
        }


        rule.onNodeWithText(medicine.name).assertIsDisplayed()
        rule.onNodeWithText("Dose: ${medicine.dose}").assertIsDisplayed()
        rule.onNodeWithText("Strength: ${medicine.strength}").assertIsDisplayed()
    }

}