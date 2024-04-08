package com.example.composetest

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.ui.theme.ComposeTestTheme

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
    Surface(color = Color.DarkGray, shadowElevation = 30.dp, modifier = Modifier.padding(3.dp)) {
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
                    containerColor = Color.White,
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

@Composable
fun BottomNavigationBar(modifier: Modifier=Modifier
){
    //하단 네비게이션바 컴포넌트 색 지정
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor=Color(0xFF03dac5),
        selectedTextColor = Color(0xFF03dac5),
        indicatorColor = Color.Gray,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.White,
        disabledIconColor = Color.Cyan,
        disabledTextColor = Color.Cyan,
    )

    var isSelected = remember { mutableStateOf(false) }

    NavigationBar(modifier.fillMaxWidth(), containerColor = Color.Gray) {
        NavigationBarItem(selected = isSelected.value,
            onClick = { isSelected.value = !isSelected.value },
            icon = { Icon(imageVector = Icons.Default.Home,
                contentDescription = "home")
            },
            colors = colors,
            label = { Text(text = "Home")},
            alwaysShowLabel = false //클릭 시 라벨이 표시되게.
        )
        NavigationBarItem(selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Home,
                contentDescription = "home",)
            }

        )
        NavigationBarItem(selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Home,
                contentDescription = "home",)
            },

        )

    }
}
@Preview
@Composable
fun PreviewBottomNavigationBar(){
    BottomNavigationBar(modifier = Modifier)

}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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
        var isClicked by remember { mutableStateOf(false) }

        Scaffold(
            bottomBar = {
               BottomNavigationBar(modifier = Modifier)
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = "Top Bar") },
                    colors = TopAppBarDefaults.topAppBarColors(Yellow),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable { })
                    },
                    actions = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                        Icon(Icons.Default.Add, contentDescription = "Search")
                    }
                )

            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        isClicked = !isClicked
                    }// FAB 내부 아이콘의 색상 설정
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    MakeRecyclerView(list1)
                }
            }
        )
        if (!isClicked){

            Box(modifier = Modifier.fillMaxSize().padding(bottom = 200.dp),
                contentAlignment = Alignment.BottomEnd,
                content = { Text(text = "String !! clicked !!")}
            )
        }

    }
}



