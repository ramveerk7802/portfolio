package com.rvcode.portfolioapp.utility

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    object Welcome: Destination()

    @Serializable
    object Introduction: Destination()

    @Serializable
    object Home: Destination()

}