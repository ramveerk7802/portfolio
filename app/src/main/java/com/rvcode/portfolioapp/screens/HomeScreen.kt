package com.rvcode.portfolioapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.fillMaxWidth(0.8f),
        drawerContent = {
            ModalDrawerSheet{
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    MyHeader()
                }

//                Box(
//                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = "Drawer Content")
//                }
            }
        }
    ){
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Profile")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.apply { if (isClosed) open() else close() } }
                            }
                        ){
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = "menu icon"
                            )
                        }

                    }

                )
            }

        )
        { innerPadding ->
            App(modifier = Modifier.padding(innerPadding))
        }

    }







}

@Composable
fun App(modifier:  Modifier) {

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