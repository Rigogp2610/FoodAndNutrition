package com.robgar.foodandnutrition.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.robgar.foodandnutrition.R
import com.robgar.foodandnutrition.data.Ingredient
import com.robgar.foodandnutrition.data.imagePath
import com.robgar.foodandnutrition.ui.common.LoadingProgressBar
import com.robgar.foodandnutrition.ui.theme.FoodAndNutritionTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    FoodAndNutritionTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClick: (ingredient: Ingredient) -> Unit, vm: HomeViewModel = viewModel()) {

    val state by vm.state.collectAsStateWithLifecycle()

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                SearchIngredientTextField(vm = vm)

                Log.d("HomeScreen", "screen: ${state.loading} | ${state.ingredients.size}")

                if (state.loading) {
                    LoadingProgressBar(modifier = Modifier.padding(padding))
                } else {
                    IngredientList(onClick = onClick, vm = vm)
                }
            }
        }
    }
}

@Composable
fun IngredientList(onClick: (ingredient: Ingredient) -> Unit, vm: HomeViewModel) {
    val state by vm.state.collectAsStateWithLifecycle()
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Adaptive(120.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(state.ingredients) { ingredient ->
            IngredientItem(
                ingredient = ingredient,
                onClick = { onClick(ingredient) })
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == state.ingredients.lastIndex) {
                    vm.searchMoreIngredients()
                }
            }
    }
}

@Composable
fun IngredientItem(ingredient: Ingredient, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            )
            .background(
                color = Color.White,
                shape = MaterialTheme.shapes.small
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ingredient.imagePath(),
            contentDescription = ingredient.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = ingredient.name,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun SearchIngredientTextField(vm: HomeViewModel) {
    val ingredientName = vm.ingredientName

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = ingredientName.value,
            onValueChange = {
                Log.d("HomeScreen", "search ingredient: ${it}")
                vm.searchIngredientsByName(it)
            },
            label = { Text(text = "Busca el ingrediente") }
        )
    }
}