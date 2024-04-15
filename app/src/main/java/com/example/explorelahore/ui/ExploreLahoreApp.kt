package com.example.explorelahore.ui

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.explorelahore.model.Place
import com.example.explorelahore.model.PlaceType
import com.example.explorelahore.ui.utils.ExploreLahoreContentType
import com.example.explorelahore.ui.utils.ExploreLahoreNavigationType
import kotlinx.coroutines.launch

@Composable
fun ExploreLahoreApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: ExploreLahoreViewModel = viewModel()
    val exploreLahoreUiState = viewModel.uiState.collectAsState().value

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val navigationType: ExploreLahoreNavigationType
    val contentType: ExploreLahoreContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ExploreLahoreNavigationType.BOTTOM_NAVIGATION
            contentType = ExploreLahoreContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ExploreLahoreNavigationType.NAVIGATION_RAIL
            contentType = ExploreLahoreContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = ExploreLahoreNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ExploreLahoreContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = ExploreLahoreNavigationType.BOTTOM_NAVIGATION
            contentType = ExploreLahoreContentType.LIST_ONLY
        }
    }

    ExploreLahoreHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        exploreLahoreUiState = exploreLahoreUiState,
        lazyListState = listState,
        onTabPressed = { placeType: PlaceType ->
            viewModel.updateCurrentType(placeType = placeType)
            viewModel.resetHomeScreenStates()
            coroutineScope.launch {
                listState.scrollToItem(0)
            }
        },
        onPlaceCardPressed = { place: Place ->
            viewModel.updateDetailsScreenStates(
                place = place
            )
        },
        onDetailsScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        modifier = modifier
    )
}