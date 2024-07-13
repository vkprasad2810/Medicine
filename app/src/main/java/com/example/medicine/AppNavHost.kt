package com.example.medicine

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.medicine.database.Medicine
import com.example.medicine.screens.GreetingScreen
import com.example.medicine.screens.LoginScreen
import com.example.medicine.screens.MedicineDetailScreen
import com.google.gson.Gson


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        // login screen
        composable("login") { LoginScreen { navController.navigate("greeting/$it") } }

        // greeting screen
        composable(
            "greeting/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) {
            val email = it.arguments?.getString("email") ?: ""
            GreetingScreen(email){ medicine->
                navController.navigate("detail/${Gson().toJson(medicine)}")
            }
        }

        // medicine details Screen
        composable(
            "detail/{medicine}",
            arguments = listOf(navArgument("medicine") { type = NavType.StringType })
        ) {
            val medicine =
                Gson().fromJson(it.arguments?.getString("medicine"), Medicine::class.java)

            if (medicine != null) {
                MedicineDetailScreen(medicine)
            }
        }
    }
}