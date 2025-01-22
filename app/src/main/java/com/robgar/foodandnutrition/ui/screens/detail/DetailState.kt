package com.robgar.foodandnutrition.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.Ingredient

@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    private val state: Result<Ingredient>,
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackbarHostState: SnackbarHostState
) {
    val ingredient: Ingredient?
        get() = (state as? Result.Success)?.data

    val topBarTitle: String
        get() = ingredient?.name ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    state: Result<Ingredient>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) = remember(state) { DetailState(state, scrollBehavior, snackbarHostState) }