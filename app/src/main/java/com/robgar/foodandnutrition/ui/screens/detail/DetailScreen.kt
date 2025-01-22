package com.robgar.foodandnutrition.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.robgar.foodandnutrition.R
import com.robgar.foodandnutrition.domain.ImageSize
import com.robgar.foodandnutrition.domain.Ingredient
import com.robgar.foodandnutrition.domain.imagePath
import com.robgar.foodandnutrition.ui.common.FNScaffold
import com.robgar.foodandnutrition.ui.common.LoadingProgressBar
import com.robgar.foodandnutrition.ui.screens.home.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val detailState = rememberDetailState(state)

    Screen {
        FNScaffold(
            state,
            topBar = {
                DetailTopAppBar(
                    title = detailState.ingredient?.name ?: "",
                    scrollBehavior = detailState.scrollBehavior,
                    onBack = onBack
                )
            },
            snackbarHost = { SnackbarHost(hostState = detailState.snackbarHostState) },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ) { padding, ingredient ->
            IngredientDetail(ingredient = ingredient, modifier = Modifier.padding(padding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(title: String, scrollBehavior: TopAppBarScrollBehavior, onBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun IngredientDetail(ingredient: Ingredient, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = ingredient.imagePath(size = ImageSize.BIG),
            contentDescription = ingredient.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )

        Text(
            text = buildAnnotatedString {
                Property(
                    name = "Proteínas",
                    value = ingredient.nutrition?.caloricBreakdown?.percentProtein.toString()
                )
                Property(
                    name = "Grasas",
                    value = ingredient.nutrition?.caloricBreakdown?.percentFat.toString()
                )
                Property(
                    name = "Carbohidratos",
                    value = ingredient.nutrition?.caloricBreakdown?.percentCarbs.toString()
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp)
        )
    }
}

@Composable
private fun AnnotatedString.Builder.Property(name: String, value: String, end: Boolean = false) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }

}