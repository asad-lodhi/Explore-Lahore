package com.example.explorelahore.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    val type: PlaceType = PlaceType.Restaurant,
    @StringRes val name: Int = -1,
    @StringRes val details: Int = -1,
    @DrawableRes val image: Int = -1

)
