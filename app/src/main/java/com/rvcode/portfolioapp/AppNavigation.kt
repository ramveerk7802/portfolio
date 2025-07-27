package com.rvcode.portfolioapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rvcode.portfolioapp.screens.ExperienceScreen
import com.rvcode.portfolioapp.screens.HomeScreen
import com.rvcode.portfolioapp.screens.IntroductionScreen
import com.rvcode.portfolioapp.screens.PortfolioScreen
import com.rvcode.portfolioapp.screens.SettingScreen
import com.rvcode.portfolioapp.screens.WelcomeScreen
import com.rvcode.portfolioapp.utility.Destination


@Composable
fun AppNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Destination.Welcome
    ){
        composable(Destination.Welcome) {
            WelcomeScreen {
                navHostController.navigate(Destination.Introduction)
            }
        }
        composable (Destination.Introduction){
            IntroductionScreen(
                onGetStartedClick = {
                    navHostController.navigate(route = Destination.Home){
                        popUpTo(route=Destination.Welcome) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

            )
        }

        composable(route = Destination.Home) {
            HomeScreen(navHostController = navHostController)
        }


    }

}