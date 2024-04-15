package com.example.explorelahore.ui

import androidx.lifecycle.ViewModel
import com.example.explorelahore.data.LocalPlacesDataProvider
import com.example.explorelahore.model.Place
import com.example.explorelahore.model.PlaceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ExploreLahoreViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ExploreLahoreUiState())
    val uiState: StateFlow<ExploreLahoreUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val places: Map<PlaceType, List<Place>> =
            LocalPlacesDataProvider.allPlaces.groupBy { it.type }
        _uiState.value =
            ExploreLahoreUiState(
                places = places,
                currentSelectedPlace = places[PlaceType.Restaurant]?.get(0)
                    ?: LocalPlacesDataProvider.defaultPlace
            )
    }

    fun updateDetailsScreenStates(place: Place) {
        _uiState.update {
            it.copy(
                currentSelectedPlace = place,
                isShowingHomepage = false
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedPlace = it.places[it.currentType]?.get(0)
                    ?: LocalPlacesDataProvider.defaultPlace,
                isShowingHomepage = true

            )
        }
    }

    fun updateCurrentType(placeType: PlaceType) {
        _uiState.update {
            it.copy(
                currentType = placeType
            )
        }
    }
}