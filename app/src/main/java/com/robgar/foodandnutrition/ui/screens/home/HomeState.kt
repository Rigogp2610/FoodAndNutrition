package com.robgar.foodandnutrition.ui.screens.home

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

class HomeState(val listState: LazyGridState) {

    @Composable
    fun PaginatedList(onNextPage: (Int) -> Unit) {
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collect { lastVisibleIndex ->
                    lastVisibleIndex?.let { onNextPage(it) }
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeState(
    listState: LazyGridState = rememberLazyGridState()
) : HomeState {
    return remember(listState) { HomeState(listState) }
}