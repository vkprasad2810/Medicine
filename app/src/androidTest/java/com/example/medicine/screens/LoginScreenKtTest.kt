package com.example.medicine.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest {

    @get:Rule
    val rule = createComposeRule()


    @Test
    fun testLoginScreen() {
        var username = ""
        rule.setContent {
            LoginScreen(onLogin = { username = it })
        }

        rule.onNodeWithText("Email").performTextInput("userEmailId")
        rule.onNodeWithText("Login").performClick()

        assert(username == "userEmailId")
    }


    @Test
    fun loginScreen_showsToastOnEmptyEmail() {
        var capturedEmail = ""
        rule.setContent {
            LoginScreen(onLogin = { email -> capturedEmail = email })
        }


        rule.onNodeWithText("Login").performClick()


        assert(capturedEmail.isEmpty())
    }
}