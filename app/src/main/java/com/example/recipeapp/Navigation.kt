package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            RecipeScreen(navController)
        }
        composable("detail/{catJson}") { backStackEntry ->
            val catJson = backStackEntry.arguments?.getString("catJson")
            val cat = Gson().fromJson(catJson, Cat::class.java)
            DetailScreen(cat, navController)
        }
    }
}
