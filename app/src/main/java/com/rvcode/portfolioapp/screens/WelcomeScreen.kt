package com.rvcode.portfolioapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rvcode.portfolioapp.R
import com.rvcode.portfolioapp.ui.theme.BLUE
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onNavigate:()->Unit){
    LaunchedEffect(key1 = Unit){
        delay(timeMillis = 2000)
        onNavigate()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            AsyncImage(
                model = R.drawable.man_icon,
                contentDescription = "icon",
                modifier = Modifier.size(120.dp)
                    .border(
                        width = 2.dp,
                        color = BLUE,
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .clip(
                        shape = CircleShape
                    )
            )

            LinearProgressIndicator(
                trackColor = BLUE,
                color = Color.Gray
            )


        }
    }

}