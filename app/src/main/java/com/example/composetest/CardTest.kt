package com.example.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Cardtest(){

val isFavorite = remember {
    mutableStateOf(false)
}
    Card(
            modifier = Modifier
                    .width(150.dp) //비율
                    .height(150.dp)
                    .padding(5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.moon_img),
                    contentDescription = "moon",
                    contentScale = ContentScale.Crop, //비율 유지
                    modifier = Modifier.fillMaxSize() //이미지가 box를 가득채우도록.(카드)
            )
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)){ //box를 Card의 사이즈에 맞춘다.
                IconButton(onClick = { isFavorite.value = !isFavorite.value }) { //여기서 colors 색 설정하면 눌렀을때, 누르지않았을때 설정가능
                    Icon(imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "favorite",
                            tint = Color.White)
                }
            }
            Text(text = "hello", color = Color.White)
        }

    }
}
@Preview
@Composable
fun Myapp() {
    Scaffold(
            content = {
                Column(
                        modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                ) {
                    // 여기에 컨텐츠를 추가하세요
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.Center) {
                            Cardtest()

                    }
                    LazyRow {
                        items(10){
                            Cardtest()
                        }
                    }
                }
            }
    )
}
