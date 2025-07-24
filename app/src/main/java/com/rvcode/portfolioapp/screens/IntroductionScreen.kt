package com.rvcode.portfolioapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.rvcode.portfolioapp.R
import com.rvcode.portfolioapp.models.PageData
import com.rvcode.portfolioapp.ui.theme.BLUE
import com.rvcode.portfolioapp.viewModels.MyPageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun IntroductionScreen(onGetStartedClick: () -> Unit) {

    val myPageViewModel : MyPageViewModel = hiltViewModel()
    val pages by  myPageViewModel.pages.observeAsState(initial = emptyList())

    val pagerState = rememberPagerState(initialPage = 0, pageCount = {pages.size})
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        while (pagerState.currentPage < pagerState.pageCount - 1){
            delay(3000L)
            pagerState.animateScrollToPage(pagerState.currentPage+1)
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        HorizontalPager(
            state = pagerState,
//            modifier = Modifier.fillMaxWidth().weight(1f)
        ){index->
            PageUi(pages[index])
        }

        // Page indicator
        PageIndicator(pagerState = pagerState)
        MyButton(pagerState, scope, onGetStartedClick = onGetStartedClick)

    }
}



//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PageUi(data: PageData) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(30.dp)
    ){
        Text(
            text = data.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = data.icon,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp)
                .border(
                    width = 2.dp,
                    color = BLUE,
                    shape = CircleShape
                )
                .padding(2.dp)
                .clip(shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = data.subtitle, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.description, style = MaterialTheme.typography.bodyMedium)

    }

}

@Composable
fun PageIndicator(pagerState: PagerState){
    Row (
        modifier = Modifier.wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        repeat(pagerState.pageCount){index->
            val color = if(pagerState.currentPage==index) Color.DarkGray else Color.LightGray
            Box(modifier = Modifier.padding(2.dp)
                .clip(CircleShape)
                .background(color = color)
                .size(16.dp)
            )
        }
    }

}


@Composable
fun MyButton(pagerState: PagerState, scope: CoroutineScope, onGetStartedClick: () -> Unit){
    val buttonText = if(pagerState.currentPage== pagerState.pageCount-1) "Get Started" else "SKIP"
    ElevatedButton(
        onClick = {
            if(pagerState.currentPage== pagerState.pageCount-1){
                    onGetStartedClick()
            }else{
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage+1)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(.7f)
    ){
        Text(text = buttonText)
    }
}