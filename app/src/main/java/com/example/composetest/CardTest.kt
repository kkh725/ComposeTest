package com.example.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Cardtest(){

Scaffold {

}
val isFavorite = remember {
    mutableStateOf(false)
}
    Card(
            modifier = Modifier
                    .fillMaxWidth(0.5f) //비율
                    .height(200.dp)
                    .padding(5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "moon",
                    contentScale = ContentScale.Crop, //비율 유지
                    modifier = Modifier.fillMaxSize() //이미지가 box를 가득채우도록.(카드)
            )
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)){ //box를 Card의 사이즈에 맞춘다.
                IconButton(onClick = { isFavorite.value = !isFavorite.value }) {
                    Icon(imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "favorite")
                }


            }
        }




    }
}