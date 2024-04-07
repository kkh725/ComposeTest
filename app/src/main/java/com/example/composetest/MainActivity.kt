package com.example.composetest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.ui.theme.ComposeTestTheme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ComposeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        GreetingPreview()

                    }
                }
            }
        }
    }




}
@Composable
fun MakeRecyclerView(list : List<Item>,modifier: Modifier=Modifier){
    LazyColumn {
        items(list) { item ->
            RowItems(item = item)
        }
    }
}
//리사이클러뷰 아이템 정의
@Composable
fun RowItems(item: Item){
    Surface(color = Color.Red, shadowElevation = 30.dp, modifier = Modifier.padding(3.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {  }){
            Image(painter = painterResource(id = item.image), contentDescription = null)
            Column (modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(0.dp,0.dp,0.dp,5.dp))
                Text(
                    text = item.age.toString(),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(0.dp,0.dp,0.dp,5.dp))
                Text(
                    text = item.age.toString(),
                    fontWeight = FontWeight.Bold,
                )
                FloatingActionButton(
                    onClick = {
                        // FAB를 클릭했을 때 수행할 동작을 여기에 작성합니다.
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(56.dp) // 크기를 여기서 조정합니다.
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }

        }
    }

}
@Preview
@Composable
fun RowItemsPreview(){
    RowItems(item = Item("kim", 1, R.drawable.ic_launcher_foreground))


}


//컴포저블 함수 자체가 그냥 하나의 view혹은 레이아웃이라고 생각하면된다.

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,viewModel: MyViewModel = MyViewModel()) {

    val mydata = viewModel.myData.observeAsState()

    Column(modifier.fillMaxWidth()) {
        Text(text = name, color = Color.White)
        Button(
            onClick = { viewModel.updateData("String") },
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp)
        ) {
            Text(text = mydata.value.toString())
        }
        Text(text = name)
    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingPreview() {
    ComposeTestTheme {
        val list1: List<Item> = listOf(
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground),
            Item("kim", 1, R.drawable.ic_launcher_foreground)
        )


        Scaffold(
            bottomBar = {
                BottomAppBar(containerColor = Color.Cyan) {
                    //주로 네비게이션 바 composable로 만들어서 사용한다.
                    Icon(Icons.Default.Search, contentDescription = "Search", modifier = Modifier.size(35.dp))
                    Icon(Icons.Default.Search, contentDescription = "Search")
                    Icon(Icons.Default.Search, contentDescription = "Search")
                    Text(text = "hihi")
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = "Top Bar") },
                    colors = TopAppBarDefaults.topAppBarColors(Yellow),
                    navigationIcon = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.clickable {  })},
                    actions = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                        Icon(Icons.Default.Add, contentDescription = "Search")
                    }
                )

            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        // FAB를 클릭했을 때 수행할 동작을 여기에 작성합니다.
                    },
                    containerColor = Color.Red,
                    contentColor = Color.White, // FAB 내부 아이콘의 색상 설정
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }

        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Text(text = "hi", color = Color.Black)
            }
        }

    }
}

