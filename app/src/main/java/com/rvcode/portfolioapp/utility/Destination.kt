package com.rvcode.portfolioapp.utility

import kotlinx.serialization.Serializable

//@Serializable
//sealed class Destination(
//    val route : String
//) {
//
//    @Serializable
//    object Welcome: Destination(route = "welcome")
//
//    @Serializable
//    object Introduction: Destination(route = "introduction")
//
//    @Serializable
//    object Home: Destination(route = "home")
//
//    @Serializable
//    object Portfolio: Destination(route = "portfolio")
//
//    @Serializable
//    object Experience: Destination(route = "experience")
//
//    @Serializable
//    object Setting: Destination(route = "setting")
//
//    @Serializable
//    object Profile: Destination(route = "profile")
//
//}


object Destination {
    const val Welcome = "welcome"
    const val Introduction = "introduction"
    const val Home = "home"
    const val Profile = "profile"
    const val Portfolio = "portfolio"
    const val Experience = "experience"
    const val Setting = "setting"
}