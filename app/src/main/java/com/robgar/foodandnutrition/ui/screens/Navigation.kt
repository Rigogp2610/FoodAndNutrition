package com.robgar.foodandnutrition.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robgar.foodandnutrition.ui.screens.detail.DetailScreen
import com.robgar.foodandnutrition.ui.screens.detail.DetailViewModel
import com.robgar.foodandnutrition.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(onClick = { ingredient -> navController.navigate("detail/${ingredient.id}") }) }

        composable(
            "detail/{ingredientId}",
            arguments = listOf(navArgument("ingredientId") { type = NavType.IntType })
        ) { backStackEntry ->
            val ingredientId = requireNotNull(backStackEntry.arguments?.getInt("ingredientId"))
            DetailScreen(
                viewModel { DetailViewModel(ingredientId) },
                onBack = {
                    navController.popBackStack()
                })
        }
    }
}