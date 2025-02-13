package com.robgar.foodandnutrition.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robgar.foodandnutrition.App
import com.robgar.foodandnutrition.framework.IngredientsLocalDataSource
import com.robgar.foodandnutrition.data.repository.IngredientsRepository
import com.robgar.foodandnutrition.framework.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.domain.repository.IIngredientsRepository
import com.robgar.foodandnutrition.domain.usecases.FetchInformationOfIngredientUseCase
import com.robgar.foodandnutrition.domain.usecases.SearchIngredientsByNameUseCase
import com.robgar.foodandnutrition.ui.screens.detail.DetailScreen
import com.robgar.foodandnutrition.ui.screens.detail.DetailViewModel
import com.robgar.foodandnutrition.ui.screens.home.HomeScreen
import com.robgar.foodandnutrition.ui.screens.home.HomeViewModel

sealed class NavScreen(val route: String) {
    data object Home : NavScreen("home")
    data object Detail : NavScreen("detail/{${NavArgs.IngredientId.key}}") {
        fun createRoute(ingredientId: Int) = "detail/$ingredientId"
    }
}

enum class NavArgs(val key: String) {
    IngredientId("ingredientId")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val ingredientsRepository : IIngredientsRepository = IngredientsRepository(
        IngredientsLocalDataSource(app.db.ingredientsDao()),
        IngredientsRemoteDataSource()
    )

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(onClick = { ingredient ->
                navController.navigate(
                    NavScreen.Detail.createRoute(ingredient.ingredientId)
                )
            }, viewModel { HomeViewModel(SearchIngredientsByNameUseCase(ingredientsRepository)) })
        }

        composable(
            NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.IngredientId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val ingredientId =
                requireNotNull(backStackEntry.arguments?.getInt(NavArgs.IngredientId.key))
            DetailScreen(
                viewModel { DetailViewModel(FetchInformationOfIngredientUseCase(ingredientsRepository), ingredientId) },
                onBack = {
                    navController.popBackStack()
                })
        }
    }
}