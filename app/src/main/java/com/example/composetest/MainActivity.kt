package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){

                        Greeting(
                            name = "Androi2d",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Greeting(name = "android22",modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
//리사이클러뷰 아이템 정의
@Composable
fun RowItems(item: Item){
    Row (verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(id = item.image), contentDescription = null)
        Column (modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            ){
            Text(
                text = item.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = item.age.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = item.age.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}


//컴포저블 함수 자체가 그냥 하나의 view혹은 레이아웃이라고 생각하면된다.

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
Text(text = name)

}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() { //매게변수를 사용하지않아야한다.
    ComposeTestTheme {
        val list1: List<Item> = listOf(
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground)
        )
        Column {
            Greeting(name = "Android")
            RowItems(item =Item("kim", 1, R.drawable.ic_launcher_foreground))
        }

    }
}