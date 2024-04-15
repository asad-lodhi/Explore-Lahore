package com.example.explorelahore.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explorelahore.R
import com.example.explorelahore.data.LocalPlacesDataProvider.allPlaces
import com.example.explorelahore.model.Place
import com.example.explorelahore.ui.theme.ExploreLahoreTheme

@Composable
fun ExploreLahoreListOnlyContent(
    exploreLahoreUiState: ExploreLahoreUiState,
    onPlaceCardPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = exploreLahoreUiState.currentPlaces

    LazyColumn(
        state = LazyListState(),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ExploreLahoreTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
        items(places, key = { place -> place.id }) { place ->
            PlaceListItem(
                place = place,
                selected = false,
                onCardClick = {
                    onPlaceCardPressed(place)
                }
            )
        }
    }
}

@Composable
fun ExploreLahoreListAndDetailContent(
    exploreLahoreUiState: ExploreLahoreUiState,
    onPlaceCardPressed: (Place) -> Unit,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    val places = exploreLahoreUiState.currentPlaces

    Row(modifier = modifier) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = 16.dp,
                    top = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(places, key = { place -> place.id}) { place ->
                PlaceListItem(
                    place = place,
                    selected = exploreLahoreUiState.currentSelectedPlace.id == place.id,
                    onCardClick = {
                        onPlaceCardPressed(place)
                    }
                )
            }
        }
        val activity = LocalContext.current as Activity
        ExploreLahoreDetailsScreen(
            exploreLahoreUiState = exploreLahoreUiState,
            onBackPressed = { activity.finish() },
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceListItem(
    place: Place,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(place.name),
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp)
                    .weight(1f)
//                    .align(Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
//                    .height(48.dp)
                    .weight(2f)
                    .clip(
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }

    }

}

@Composable
private fun ExploreLahoreTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
//            .padding(vertical = 10.dp)
//            .height(64.dp)
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .height(72.dp)
//            .padding(8.dp)
//            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
//            modifier = modifier
//            modifier = Modifier.padding(start = 4.dp)
//            modifier = Modifier.padding(start = 8.dp)
        )
        Image(
            painter = painterResource(R.drawable.pak),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
//                .fillMaxHeight()
//                .wrapContentSize()
//                .wrapContentHeight()
                .height(80.dp)
//                .width(128.dp)
//                .wrapContentWidth()
//                .width(64.dp)

//                .wrapContentSize()
//                .padding(end = 8.dp)
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

@Preview(showBackground = true)
@Composable
fun ExploreLahoreTopBarPreview() {
    ExploreLahoreTheme {
        Surface {
            ExploreLahoreTopBar()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceListItemPreview() {
    ExploreLahoreTheme {
        Surface {
            PlaceListItem(
                onCardClick = {},
                place = allPlaces[0],
                selected = false
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ExploreLahoreListOnlyContentPreview() {
//    ExploreLahoreTheme {
//        Surface {
//            ExploreLahoreListOnlyContent(
//                exploreLahoreUiState = ExploreLahoreUiState(
//                    places = mapOf(
//                        PlaceType.Restaurant to allPlaces,
//                        // Add other PlaceType entries as needed
//                    ),
////                    currentType = PlaceType.Restaurant
//                ),
//                onPlaceCardPressed = {}
//            )
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun ExploreLahoreListAndDetailContentPreview() {
//    ExploreLahoreTheme {
//        Surface {
//            ExploreLahoreListAndDetailContent(
//                exploreLahoreUiState = ExploreLahoreUiState(
//                    places = mapOf(
//                        PlaceType.Restaurant to allPlaces,
//                        // Add other PlaceType entries as needed
//                    ),
//                    currentSelectedPlace = LocalPlacesDataProvider.allPlaces[0],
//                ),
//                onPlaceCardPressed = { /* Implement the function as needed */ }
//            )
//        }
//    }
//}

