package com.rvcode.portfolioapp.screens


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rvcode.portfolioapp.R
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen (){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val isModalBottomSheet = remember{ mutableStateOf(false) }
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ProfileTopAppBar(scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            IconButton(onClick = {
                isModalBottomSheet.value=true
            }){
                Icon(
                    painter = painterResource(R.drawable.contact_icon),
                    contentDescription = "contact icon"
                )
            }
        }
    ) { innerPadding ->
        ScrollContent(modifier = Modifier.padding(innerPadding))
        if(isModalBottomSheet.value) {
            MyBottomSheet(
                isModalBottomSheet = isModalBottomSheet,
                onClickGithub = {
                    isModalBottomSheet.value=false
                    openUrl(context,"https://github.com/ramveerk7802")
                },
                onClickLinkedin = {
                    isModalBottomSheet.value=false
                    openUrl(context= context, url = "https://www.linkedin.com/in/ramveerk7802/")
                },
                onClickInstagram = {
                    isModalBottomSheet.value=false
                    openUrl(context = context, url = "https://www.instagram.com/ramveer_official_/")
                },
                onClickConnectMe = {
                    isModalBottomSheet.value=false
                    sendEmail(
                        context = context,
                        emailAddress = "ramveerk7802@gmail.com",
                        subject = "Let's Connect!",
                        body = """
                            Hi Ramveer,
                            I came across your portfolio and would like to connect with you.
                            Thanks!
                            """.trimIndent()
                    )
                }

            )
        }
    }
}


@Composable
fun ScrollContent(modifier: Modifier){

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(20){
            Text(text = "Item $it",
                modifier= Modifier.padding(4.dp))
        }


    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(scrollBehavior: TopAppBarScrollBehavior){
    val collapsedAlpha = scrollBehavior.state.collapsedFraction

    LargeTopAppBar(
        expandedHeight = 270.dp,
        windowInsets = WindowInsets(0,0,0,0),
        scrollBehavior = scrollBehavior,

        title = {
            if(scrollBehavior.state.collapsedFraction>0.5f){
                Text(text = "Ramveer")
            }
            else{
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                    ){
                    Text(
                        text = "\uD83D\uDC4B Nice to meet you",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    AsyncImage(
                        model = R.drawable.my_photo,
                        contentDescription = "user photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(90.dp)
                            .border(width = 2.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Cyan,
                                        Color.Blue
                                    )
                                ),
                                shape = CircleShape
                            )
                            .padding(3.dp)
                            .clip(shape = CircleShape)
                    )
                    Text(
                        text = "Ramveer",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Android  Developer",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
        navigationIcon = {
            if(scrollBehavior.state.collapsedFraction>0.5f){
                UserImage(icon = R.drawable.my_photo)
            }
        }

    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(isModalBottomSheet: MutableState<Boolean>, onClickGithub:()-> Unit, onClickLinkedin: () -> Unit, onClickInstagram: () -> Unit, onClickConnectMe: () -> Unit){
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = {
            isModalBottomSheet.value=false
        },
        sheetState = modalBottomSheetState


    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            UserImage(icon = R.drawable.my_photo)
            Column (
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ){
                Text(text = "Ramveer",
                    style = MaterialTheme.typography.titleMedium)
                Text(text = "ramveerk7802@gmail.com")
            }


        }
        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        HorizontalDivider(
            thickness = 2.dp,
            modifier = Modifier.fillMaxWidth().height(10.dp))
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        MyCustomView(icon = R.drawable.github_icon, title = "Github", onclick = onClickGithub)
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        MyCustomView(icon = R.drawable.linkedin_icon, title = "LinkedIn", onclick = onClickLinkedin)
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        MyCustomView(icon = R.drawable.instagram_icon, title = "Instagram", onclick = onClickInstagram)
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(4.dp))
        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        Box(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
                .clickable {

                    onClickConnectMe()
                },
            contentAlignment = Alignment.TopCenter
        )
        {
            Column (
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "You can get touch with me")
                Text(text = "\u2709 ramveerk7802@gmail.com")

            }

        }


    }
}



@Composable
fun UserImage(icon: Int){
    AsyncImage(
        model = icon,
        contentDescription = "User photo",
        contentScale = ContentScale.Crop,
        modifier = Modifier.padding(horizontal = 10.dp).size(50.dp)

            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Cyan,
                        Color.Blue)
                ),
                shape = CircleShape
            ).padding(3.dp)
            .clip(shape = CircleShape)



    )

}

@Composable
fun MyCustomView(icon: Int,title: String, onclick:()->Unit){
    Box(
        modifier = Modifier.fillMaxWidth().clickable { onclick() },
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)

        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "$title icon"
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


fun openUrl(context: Context, url : String){
    val  intent = Intent( Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)

}


fun sendEmail(
    context: Context,
    emailAddress: String,
    subject: String = "",
    body: String = ""
) {

    val encodedSubject = Uri.encode(subject)
    val encodedBody = Uri.encode(body)

    val uri = Uri.parse("mailto:$emailAddress?subject=$encodedSubject&body=$encodedBody")

    val intent = Intent(Intent.ACTION_SENDTO, uri)

    // Launch the email app
    context.startActivity(intent)
}


