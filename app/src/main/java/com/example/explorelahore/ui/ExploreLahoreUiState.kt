package com.example.explorelahore.ui

import com.example.explorelahore.data.LocalPlacesDataProvider
import com.example.explorelahore.model.Place
import com.example.explorelahore.model.PlaceType

data class ExploreLahoreUiState(
    val places: Map<PlaceType, List<Place>> = emptyMap(),
    val currentType: PlaceType = PlaceType.Restaurant,
    val currentSelectedPlace: Place = LocalPlacesDataProvider.defaultPlace,
    val isShowingHomepage: Boolean = true
) {
    val currentPlaces: List<Place> by lazy { places[currentType]!! }
}