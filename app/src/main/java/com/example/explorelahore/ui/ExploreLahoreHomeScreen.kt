package com.example.explorelahore.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explorelahore.R
import com.example.explorelahore.model.Place
import com.example.explorelahore.model.PlaceType
import com.example.explorelahore.ui.theme.ExploreLahoreTheme
import com.example.explorelahore.ui.utils.ExploreLahoreContentType
import com.example.explorelahore.ui.utils.ExploreLahoreNavigationType

@Composable
fun ExploreLahoreHomeScreen(
    navigationType: ExploreLahoreNavigationType,
    contentType: ExploreLahoreContentType,
    exploreLahoreUiState: ExploreLahoreUiState,
    lazyListState: LazyListState,
    onTabPressed: (PlaceType) -> Unit,
    onPlaceCardPressed: (Place) -> Unit,
    onDetailsScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            placeType = PlaceType.Restaurant,
            icon = painterResource(R.drawable.round_fastfood_24),
            text = stringResource(R.string.restaurants)

        ),
        NavigationItemContent(
            placeType = PlaceType.Cafe,
            icon = painterResource(R.drawable.round_coffee_24),
            text = stringResource(R.string.cafes)
        ),
        NavigationItemContent(
            placeType = PlaceType.ShoppingMall,
            icon = painterResource(R.drawable.round_shopping_bag_24),
            text = stringResource(R.string.shopping_malls)
        )
    )

    if (navigationType == ExploreLahoreNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(modifier = Modifier.width(240.dp)) {
                    NavigationDrawerContent(
                        selectedDestination = exploreLahoreUiState.currentType,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(12.dp)
                    )
                }
            }
        ) {
            ExploreLahoreAppContent(
                navigationType = navigationType,
                contentType = contentType,
                lazyListState = lazyListState,
                exploreLahoreUiState = exploreLahoreUiState,
                onTabPressed = onTabPressed,
                onPlaceCardPressed = onPlaceCardPressed,
                navigationItemContentList = navigationItemContentList
            )

        }
    } else {
        if (exploreLahoreUiState.isShowingHomepage) {
            ExploreLahoreAppContent(
                navigationType = navigationType,
                contentType = contentType,
                lazyListState = lazyListState,
                exploreLahoreUiState = exploreLahoreUiState,
                onTabPressed = onTabPressed,
                onPlaceCardPressed = onPlaceCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        } else {
            ExploreLahoreDetailsScreen(
                exploreLahoreUiState = exploreLahoreUiState,
                isFullScreen = true,
                onBackPressed = onDetailsScreenBackPressed,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun ExploreLahoreAppContent(
    navigationType: ExploreLahoreNavigationType,
    contentType: ExploreLahoreContentType,
    exploreLahoreUiState: ExploreLahoreUiState,
    lazyListState: LazyListState,
    onTabPressed: ((PlaceType) -> Unit),
    onPlaceCardPressed: (Place) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
//    Box(modifier = modifier) {
    Row(modifier = modifier) {
        AnimatedVisibility(visible = navigationType == ExploreLahoreNavigationType.NAVIGATION_RAIL)
        {
            ExploreLahoreNavigationRail(
                currentTab = exploreLahoreUiState.currentType,
                onTabPressed = onTabPressed,
                navigationItemContentList = navigationItemContentList
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            if (contentType == ExploreLahoreContentType.LIST_AND_DETAIL) {
                ExploreLahoreListAndDetailContent(
                    exploreLahoreUiState = exploreLahoreUiState,
                    onPlaceCardPressed = onPlaceCardPressed,
                    lazyListState = lazyListState,
                    modifier = Modifier.weight(1f)
                )
            } else {
                ExploreLahoreListOnlyContent(
                    exploreLahoreUiState = exploreLahoreUiState,
                    onPlaceCardPressed = onPlaceCardPressed,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                )
            }

            AnimatedVisibility(visible = navigationType == ExploreLahoreNavigationType.BOTTOM_NAVIGATION)
            {
                ExploreLahoreBottomNavigationBar(
                    currentTab = exploreLahoreUiState.currentType,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
//    }
}


@Composable
private fun ExploreLahoreBottomNavigationBar(
    currentTab: PlaceType,
    onTabPressed: ((PlaceType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.placeType,
                onClick = { onTabPressed(navItem.placeType) },
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Composable
private fun ExploreLahoreNavigationRail(
    currentTab: PlaceType,
    onTabPressed: ((PlaceType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.placeType,
                onClick = { onTabPressed(navItem.placeType) },
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedDestination: PlaceType,
    onTabPressed: ((PlaceType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.placeType,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.placeType) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Image(
            painter = painterResource(R.drawable.pak),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .clip(
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.dp,
                    brush = SolidColor(value = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }

}


private data class NavigationItemContent(
    val placeType: PlaceType,
    val icon: Painter,
    val text: String
)

@Preview(showBackground = true)
@Composable
fun ExploreLahoreBottomNavigationBarPreview() {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            placeType = PlaceType.Restaurant,
            icon = painterResource(R.drawable.round_fastfood_24),
            text = stringResource(R.string.restaurants)

        ),
        NavigationItemContent(
            placeType = PlaceType.Cafe,
            icon = painterResource(R.drawable.round_coffee_24),
            text = stringResource(R.string.cafes)
        ),
        NavigationItemContent(
            placeType = PlaceType.ShoppingMall,
            icon = painterResource(R.drawable.round_shopping_bag_24),
            text = stringResource(R.string.shopping_malls)
        )
    )
    ExploreLahoreTheme {
        Surface {
            ExploreLahoreBottomNavigationBar(
                currentTab = PlaceType.Restaurant,
                onTabPressed = {},
                navigationItemContentList = navigationItemContentList
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreLahoreNavigationRailPreview() {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            placeType = PlaceType.Restaurant,
            icon = painterResource(R.drawable.round_fastfood_24),
            text = stringResource(R.string.restaurants)

        ),
        NavigationItemContent(
            placeType = PlaceType.Cafe,
            icon = painterResource(R.drawable.round_coffee_24),
            text = stringResource(R.string.cafes)
        ),
        NavigationItemContent(
            placeType = PlaceType.ShoppingMall,
            icon = painterResource(R.drawable.round_shopping_bag_24),
            text = stringResource(R.string.shopping_malls)
        )
    )
    ExploreLahoreTheme {
        Surface {
            ExploreLahoreNavigationRail(
                currentTab = PlaceType.Restaurant,
                onTabPressed = {},
                navigationItemContentList = navigationItemContentList
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerContentPreview() {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            placeType = PlaceType.Restaurant,
            icon = painterResource(R.drawable.round_fastfood_24),
            text = stringResource(R.string.restaurants)

        ),
        NavigationItemContent(
            placeType = PlaceType.Cafe,
            icon = painterResource(R.drawable.round_coffee_24),
            text = stringResource(R.string.cafes)
        ),
        NavigationItemContent(
            placeType = PlaceType.ShoppingMall,
            icon = painterResource(R.drawable.round_shopping_bag_24),
            text = stringResource(R.string.shopping_malls)
        )
    )
    ExploreLahoreTheme {
        Surface {
            NavigationDrawerContent(
                selectedDestination = PlaceType.Restaurant,
                onTabPressed = {},
                navigationItemContentList = navigationItemContentList
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ExploreLahoreHomeScreenPreview() {
//    val viewModel = ExploreLahoreViewModel()
//    ExploreLahoreTheme {
//        Surface {
//            ExploreLahoreHomeScreen(
//                navigationType = ExploreLahoreNavigationType.BOTTOM_NAVIGATION,
//                contentType = ExploreLahoreContentType.LIST_ONLY,
//                exploreLahoreUiState = ExploreLahoreUiState(
//                    places = LocalPlacesDataProvider.allPlaces.groupBy { it.type }
//                )
//
////                exploreLahoreUiState = ExploreLahoreUiState(
////                    places = LocalPlacesDataProvider.allPlaces.groupBy { it.type },
////                    currentSelectedPlace = LocalPlacesDataProvider.allPlaces.groupBy { it.type }[PlaceType.Restaurant]?.get(
////                        0
////                    )
////                        ?: LocalPlacesDataProvider.defaultPlace()
////                )
//
////                exploreLahoreUiState = ExploreLahoreUiState(
////                    places = mapOf(
////                        PlaceType.Restaurant to LocalPlacesDataProvider.allPlaces,
////                        // Add other PlaceType entries as needed
////                    ))
//                ,
//                onTabPressed = { placeType: PlaceType ->
//                    viewModel.updateCurrentType(placeType = placeType)
////                    viewModel.resetHomeScreenStates()
//                },
//                onPlaceCardPressed = { place: Place ->
//                    viewModel.updateDetailsScreenStates(
//                        place = place
//                    )
//                },
//                onDetailsScreenBackPressed = { /*TODO*/ }
//            )
//        }
//    }
//}