package com.robgar.foodandnutrition.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.robgar.foodandnutrition.R
import com.robgar.foodandnutrition.data.ImageSize
import com.robgar.foodandnutrition.data.imagePath
import com.robgar.foodandnutrition.ui.common.LoadingProgressBar
import com.robgar.foodandnutrition.ui.screens.home.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val state by vm.state.collectAsStateWithLifecycle()

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = state.ingredient?.name ?: "") }, navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                })
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                if (state.loading) {
                    LoadingProgressBar(modifier = Modifier.padding(padding))
                }

                state.ingredient?.let { ingredient ->
                    AsyncImage(
                        model = ingredient.imagePath(size = ImageSize.BIG),
                        contentDescription = ingredient.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )

                    Text(text = ingredient.name, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}