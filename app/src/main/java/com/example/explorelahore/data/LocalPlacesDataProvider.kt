package com.example.explorelahore.data

import com.example.explorelahore.R
import com.example.explorelahore.model.Place
import com.example.explorelahore.model.PlaceType

object LocalPlacesDataProvider {
    var lastid = 0
    val allPlaces = listOf(
        Place(
            id = lastid++,
            type = PlaceType.Restaurant,
            name = R.string.bae,
            details = R.string.d,
            image = R.drawable.bae
        ),
        Place(
            id = lastid++,
            type = PlaceType.Restaurant,
            name = R.string.big_moes,
            details = R.string.d,
            image = R.drawable.bigmoes
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.coffee_bar,
            details = R.string.d,
            image = R.drawable.coffeebar
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.coffee_planet,
            details = R.string.d,
            image = R.drawable.coffeeplanet
        ),
        Place(
            id = lastid++,
            type = PlaceType.ShoppingMall,
            name = R.string.emporium,
            details = R.string.d,
            image = R.drawable.emporium
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.espresso,
            details = R.string.d,
            image = R.drawable.espresso
        ),
        Place(
            id = lastid++,
            type = PlaceType.ShoppingMall,
            name = R.string.fortress_square,
            details = R.string.d,
            image = R.drawable.fortresssquare
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.gloria_jeans,
            details = R.string.d,
            image = R.drawable.gloriajeans
        ),
        Place(
            id = lastid++,
            type = PlaceType.ShoppingMall,
            name = R.string.gulberg_galleria,
            details = R.string.d,
            image = R.drawable.gulberggalleria
        ),
        Place(
            id = lastid++,
            type = PlaceType.Restaurant,
            name = R.string.jessies_burgers,
            details = R.string.d,
            image = R.drawable.jessiesburgers
        ),
        Place(
            id = lastid++,
            type = PlaceType.Restaurant,
            name = R.string.just_burgers,
            details = R.string.d,
            image = R.drawable.justburgers
        ),
        Place(
            id = lastid++,
            type = PlaceType.ShoppingMall,
            name = R.string.mall_of_lahore,
            details = R.string.d,
            image = R.drawable.malloflahore
        ),
        Place(
            id = lastid++,
            type = PlaceType.Restaurant,
            name = R.string.ministry_of_burgers,
            details = R.string.d,
            image = R.drawable.ministryofburgers
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.mocca_coffee,
            details = R.string.d,
            image = R.drawable.moccacoffee
        ),
        Place(
            id = lastid++,
            type = PlaceType.ShoppingMall,
            name = R.string.packages_mall,
            details = R.string.d,
            image = R.drawable.packagesmall
        ),
        Place(
            id = lastid++,
            type = PlaceType.Restaurant,
            name = R.string.pasta_la_vista,
            details = R.string.d,
            image = R.drawable.pastalavista
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.second_cup,
            details = R.string.d,
            image = R.drawable.secondcup
        ),
        Place(
            id = lastid++,
            type = PlaceType.Cafe,
            name = R.string.the_coffee_bean_and_tea_leaf,
            details = R.string.d,
            image = R.drawable.thecoffeebeanandtealeaf
        ),
        Place(
            id = lastid++,
            type = PlaceType.ShoppingMall,
            name = R.string.xinhua_mall,
            details = R.string.d,
            image = R.drawable.xinhuamall
        )
    )

    val defaultPlace = Place(
        id = -1
    )

//    fun defaultPlace(): Place {
//        return allPlaces.first()
//    }
}