package com.example.explorelahore.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explorelahore.data.LocalPlacesDataProvider
import com.example.explorelahore.model.Place
import com.example.explorelahore.ui.theme.ExploreLahoreTheme

@Composable
fun ExploreLahoreDetailsScreen(
    exploreLahoreUiState: ExploreLahoreUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
            .padding(top = 16.dp)
    ) {
            if (isFullScreen) {
                ExploreLahoreDetailsScreenTopBar(
                    onBackButtonClicked = onBackPressed,
                    exploreLahoreUiState = exploreLahoreUiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
            ExploreLahoreDetailsCard(
                place = exploreLahoreUiState.currentSelectedPlace,
                isFullScreen = isFullScreen,
                modifier = if (isFullScreen) {
                    Modifier.padding(horizontal = 24.dp)
                } else {
                    Modifier.padding(end = 24.dp)
                }
            )
    }
}

@Composable
private fun ExploreLahoreDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    exploreLahoreUiState: ExploreLahoreUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
//            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        Text(
            text = stringResource(exploreLahoreUiState.currentSelectedPlace.name),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ExploreLahoreDetailsCard(
    place: Place,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .weight(1f)
//                    .height(300.dp)

            )
            if (isFullScreen) {
                Spacer(modifier = Modifier.height(12.dp))
            } else {
                Text(
                    text = stringResource(place.name),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 8.dp
                    )
                )
            }
            Text(
                text = stringResource(place.details),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .verticalScroll(
                        state = rememberScrollState(0)
                    )
                    .weight(1f)
            )

//            LazyColumn() {
//                item {
//                    Text(
//                        text = stringResource(place.details),
//                        style = MaterialTheme.typography.bodyLarge,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//
//            }

        }
    }
}

//@Composable
//private fun DetailsScreenHeader(
//    place: Place,
//    modifier: Modifier = Modifier
//) {
//
//
//}

@Preview(showBackground = true)
@Composable
fun ExploreLahoreDetailsScreenTopBarPreview() {
    ExploreLahoreTheme {
        Surface {
            ExploreLahoreDetailsScreenTopBar(
                onBackButtonClicked = { /*TODO*/ },
                exploreLahoreUiState = ExploreLahoreUiState()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreLahoreDetailsCardPreview() {
    ExploreLahoreTheme {
        Surface {
            ExploreLahoreDetailsCard(
                place = LocalPlacesDataProvider.allPlaces[0]
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreLahoreDetailsScreenPreview() {
    ExploreLahoreTheme {
        Surface {
            ExploreLahoreDetailsScreen(
                exploreLahoreUiState = ExploreLahoreUiState(
                    currentSelectedPlace =
                    LocalPlacesDataProvider.allPlaces[0]
                ),
                onBackPressed = { /*TODO*/ },
                isFullScreen = true
            )
        }
    }
}