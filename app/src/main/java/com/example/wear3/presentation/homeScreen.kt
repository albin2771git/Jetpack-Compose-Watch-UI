package com.example.wear3.presentation

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListAnchorType
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.items
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import com.example.wear3.R
import com.example.wear3.presentation.theme.Purple200
import com.example.wear3.presentation.theme.Purple500
import com.example.wear3.presentation.theme.Purple700
import com.example.wear3.presentation.theme.WearAppTheme
import com.example.wear3.presentation.theme.black
import com.example.wear3.presentation.theme.darkBlue
import com.example.wear3.presentation.theme.greyMedium
import com.example.wear3.presentation.theme.lightBlue
import com.example.wear3.presentation.theme.rose
import com.example.wear3.presentation.theme.white
import com.example.wear3.presentation.theme.yellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier

@Composable
fun splashScreenWidget(
    navController: NavHostController
) {
    LaunchedEffect(true) {
        delay(3000)
        navController.navigate("home")
    }
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = black),
        contentAlignment = Alignment.Center


    )
    {
        Image(
            painter = painterResource(
                id = R.drawable.edisapplogo
            ), contentDescription = "logo",
            modifier = androidx.compose.ui.Modifier
                .height(60.dp)
                .width(120.dp)
                .clickable {
                    navController.navigate("screen2")
                }
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun homeScreen(
    navHostController: NavHostController
) {
    WearAppTheme {
        val listState = rememberScalingLazyListState()
        Scaffold(
            vignette = {
                Vignette(vignettePosition = VignettePosition.TopAndBottom)
            },
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = listState
                )
            }

        )
        {
            //--------------------------------------------------Rotary switch functionality
            val focusRequester = remember { FocusRequester() }
            val coroutineScope = rememberCoroutineScope()
            ScalingLazyColumn(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .onRotaryScrollEvent {
                        coroutineScope.launch {
                            listState.scrollBy(it.verticalScrollPixels)
                        }
                        true
                    }
                    .focusRequester(focusRequester)
                    .focusable(),
                state = listState,

                //--------------------------------------------
                autoCentering = AutoCenteringParams(itemIndex = 2),
                userScrollEnabled = true,
                contentPadding = PaddingValues(horizontal = 0.dp),

                anchorType = ScalingLazyListAnchorType.ItemCenter
            ) {
//                item{
//                  logoTile()
//                }

                item {
                    profileTilesection()
                }

                item{
                    quickViewHead(name = "Menu List")
                }
                item {
                    menuListTile(
                        navHostController,
                        iconname = R.drawable.attendance,
                        menuName = "Attendance Information",
                        color = lightBlue
                    )
                }
                item {
                    menuListTile(
                        navHostController,
                        iconname = R.drawable.fees,
                        menuName = "Fee Information",
                        color = lightBlue
                    )
                }
                item {
                    menuListTile(
                        navHostController,
                        iconname = R.drawable.news,
                        menuName = "News",
                        color = lightBlue
                    )
                }

                item {
                    menuListTile(
                        navHostController,
                        iconname = R.drawable.account,
                        menuName = "Account Information",
                        color = lightBlue
                    )
                }
                item{
                    quickViewHead(name = "Fee Quick View")
                }
                item{
                    feeScrollView()
                }
                item{
                    quickViewHead(name = "Attendance Quick View")
                }
                item {
                    quickViewTile()
                }
            }
            LaunchedEffect(Unit) { focusRequester.requestFocus() }
        }
    }
}
@Composable
fun demoline(){
    Box(modifier = androidx.compose.ui.Modifier
        .fillMaxWidth()
        .height(2.dp)
        .background(white)
    )
}
@Composable
fun logoTile(){
    Box(modifier = androidx.compose.ui.Modifier
        .fillMaxWidth()
        .height(20.dp),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.edisapplogo), contentDescription = "logo")
    }
}
@Composable
fun quickViewTile() {
    LazyRow(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        state = rememberLazyListState()
    ) {
        item {
            quickViewTileBox(
                color = Purple200,
                name = "Staff",
                totalCount = "132",
                presentCount = "120",
                absentCount = "12"
            )
        }
        item {
            quickViewTileBox(
                color = lightBlue,
                name = "Boys",
                totalCount = "679",
                presentCount = "631",
                absentCount = "48"
            )
        }
        item {
            quickViewTileBox(
                color = rose,
                name = "Girls",
                totalCount = "597",
                presentCount = "577",
                absentCount = "20"
            )
        }
    }
}

@Composable
fun quickViewTileBox(
    color: Color = Purple200,
    name: String = "Staff",
            totalCount: String = "123",
    absentCount: String = "123",
    presentCount: String = "123",
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .padding(horizontal = 5.dp, vertical = 3.dp)
            .height(60.dp)
            .width(90.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = color),
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Text(
                text = "$name Attendance Count",
                style = TextStyle(
                    color = white,
                    fontWeight = FontWeight.W400,
                    fontSize = 7.sp
                )
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(5.dp))
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(text = "Total $name : ", style = TextStyle(
                   fontSize = 6.sp,
                   color = white
               )
               )
               Text(text = "$totalCount",style = TextStyle(
                       fontSize = 8.sp,
                   color = white
               ))
           }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Present  : ", style = TextStyle(
                    fontSize = 6.sp,
                    color = white
                )
                )
                Text(text = "$presentCount",style = TextStyle(
                    fontSize = 8.sp,
                    color = white
                ))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Absentees  : ", style = TextStyle(
                    fontSize = 6.sp,
                    color = white
                )
                )
                Text(text = "$absentCount",style = TextStyle(
                    fontSize = 8.sp,
                    color = white
                ))
            }
        }
    }
}

@Composable
fun menuListTile(
    navHostController: NavHostController,
    menuName: String = "menu",
    color: Color = Purple700,
    @DrawableRes iconname: Int
) {
    Box(modifier = androidx.compose.ui.Modifier
        .padding(horizontal = 10.dp, vertical = 6.dp)
        .clip(shape = RoundedCornerShape(18.dp))
        .fillMaxWidth()
        .background(color = color)
        .height(35.dp)
        .clickable {
            navHostController.navigate("attendance")
        },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = iconname), contentDescription = "icon",
                modifier = androidx.compose.ui.Modifier
                    .height(18.dp)
                    .padding(horizontal = 8.dp)
            )

            Text(
                text = "$menuName",
                style = TextStyle(
                    color = white,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp,

                    )

            )
        }
    }
}

@Composable
fun profileTilesection(
    user: String = "Romeo"
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = androidx.compose.ui.Modifier
                .height(30.dp)
                .width(30.dp)
                .clip(shape = CircleShape)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.profileimg
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "profile",
            )
        }
        Spacer(modifier = androidx.compose.ui.Modifier.width(10.dp))
        Column {
            Text(
                text = "Hello $user",
                style = TextStyle(
                    color = white,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp
                )
            )
            Text(
                text = "Good Morning Wish you hava a good day!",
                style = TextStyle(
                    color = white,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.W300,
                    fontSize = 6.sp
                )
            )
        }
    }
}
@Composable
fun quickViewHead(
    name: String="Quick View"
){
    Row(

        modifier = androidx.compose.ui.Modifier.padding(10.dp,3.dp,0.dp,0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$name",
            style = TextStyle(
                color = white,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                fontSize = 8.sp
            )
        )
        Spacer(modifier = androidx.compose.ui.Modifier.width(4.dp))
        Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .height(0.7.dp)
                .background(white)
        )
    }
}
@Composable
fun feeScrollView(){
    LazyRow(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        state = rememberLazyListState()
    ) {
        item {
            countWidget(
                title = "Today's Fee Collected",
                amount = "6,12,234"
            )
        }
        item {
            countWidget(
                title = "Yesterday's Fee Collected",
                amount = "6,17,834"
            )
        }
        item {
            countWidget(
                title = "This week Collected",
                amount = "76,17,834"
            )
        }
    }
}
@Composable
fun countWidget(
    title:String="Total Fee Collected",
    amount:String="6,12,234",

){
    Box(modifier = androidx.compose.ui.Modifier
        .padding(horizontal = 5.dp)
        .fillMaxWidth()
        .height(40.dp),
        contentAlignment = Alignment.Center
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "$title",
                style = TextStyle(fontSize = 8.sp,
                    fontWeight = FontWeight.W400)
            )
            Text(
                text = " Rs. $amount",
                style = TextStyle(fontSize = 12.sp,
                    fontWeight = FontWeight.W700)
            )
        }
    }
}
//---------------swipe to back option
@Composable
fun attendacePage(
    navHostController: NavHostController,

){
    val state = rememberSwipeToDismissBoxState()
    SwipeToDismissBox(
        onDismissed = {
            navHostController.navigate("home")

        },
    ) {
        Box(modifier = androidx.compose.ui.Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Attendace Information", style = TextStyle(color= white))
        }
    }

}