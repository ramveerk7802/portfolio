package com.rvcode.portfolioapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rvcode.portfolioapp.R
import com.rvcode.portfolioapp.ui.theme.BLUE
import com.rvcode.portfolioapp.utility.Destination
import com.rvcode.portfolioapp.viewModels.HomeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val internalNavHostController = rememberNavController()
    val currentBackStack by internalNavHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val title = when(currentRoute){
        Destination.Profile-> "Profile"
        Destination.Portfolio-> "Portfolio"
        Destination.Experience -> "Experience"
        Destination.Setting-> "Setting"
        else -> "Profile"
    }

    val homeViewModel : HomeViewModel = hiltViewModel()
    val navigationItemList = homeViewModel.navigationItemList.observeAsState(emptyList()).value
    var selectedNavigation  = getIndexFromRoute(currentRoute)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.8f)
            ){
                Column (
                    modifier = Modifier.wrapContentWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    MyHeader()

                    navigationItemList.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            onClick = {
                                selectedNavigation = index
                                scope.launch { drawerState.close() }
                               val route =  when(index){
                                   1-> Destination.Portfolio
                                   2-> Destination.Experience
                                   3-> Destination.Setting
                                   else -> Destination.Profile
                                }
                                internalNavHostController.navigate(route){
                                    popUpTo(Destination.Profile){
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = null
                                )
                            },
                            selected = selectedNavigation==index,
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

                        )
                    }
                }
            }
        }
    ){
        Scaffold (
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Cyan,
                                BLUE
                            )
                        )
                    ),
                    title = {
                        Text(text = title)
                    },
                    actions = {
                        IconButton(
                            onClick = {}
                        ){
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.apply { open() } }
                            }
                        ){
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = "menu icon"
                            )
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )

                )
            },
            bottomBar = {
                NavigationBar{
                    navigationItemList.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedNavigation==index,
                            onClick = {
                                selectedNavigation = index
                                val route =  when(index){
                                    1-> Destination.Portfolio
                                    2-> Destination.Experience
                                    3-> Destination.Setting
                                    else -> Destination.Profile
                                }
                                internalNavHostController.navigate(route){
                                    popUpTo(Destination.Profile){
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(text = item.title)
                            }
                        )


                    }


                }
            },

        )
        { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HomeContentNavHost(
                    internalNavHostController
                )
            }
        }

    }







}

@Composable
fun HomeContentNavHost(internalNavHostController: NavHostController) {

    NavHost(
        navController = internalNavHostController,
        startDestination = Destination.Profile
    ){
        composable(Destination.Profile) {
            ProfileScreen()
        }
        composable(Destination.Portfolio) {
            PortfolioScreen()
        }
        composable(Destination.Experience){
            ExperienceScreen()
        }

        composable(Destination.Setting) {
            SettingScreen()
        }

    }



}



@Composable
fun MyHeader(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Header")

    }
}


fun getIndexFromRoute(route: String?): Int {
    return when (route) {
        Destination.Profile -> 0
        Destination.Portfolio -> 1
        Destination.Experience -> 2
        Destination.Setting -> 3
        else -> 0
    }
}
