package com.example.composetest

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.ui.theme.ComposeTestTheme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlin.system.measureTimeMillis

interface JsonPlaceHolderApi{
    @GET("/todos")
    suspend fun getTodos():List<JsonPlaceHolderApiItem>
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val api = Retrofit
            .Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JsonPlaceHolderApi::class.java)

        lifecycleScope.launch { //레트로핏 자체에서 withContext를 활용해 io 디스패처로 진입함.
            val time = measureTimeMillis {
//                api.getTodos()
//                api.getTodos()

                val todos = async { api.getTodos() }
                val todos2 = async { api.getTodos() }
                todos.await()
                todos2.await()
            }
            Log.d("api 호출 시간","time : $time")

        }

        setContent {

                        GreetingPreview()



        }
    }
}

//리사이클러뷰 아이템 정의
@Composable
fun RowItems(item: Item){

    var isTest by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = if (isTest) Color.White else Color.Gray, label = "")


    Surface(
        color = color,
        shadowElevation = 30.dp,
        modifier = Modifier.padding(3.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { }){
                Image(painter = painterResource(id = item.image), contentDescription = null)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { isTest = !isTest }
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
                }

            }
            if (isTest){
                Spacer(modifier = Modifier.padding(0.dp,0.dp,0.dp,5.dp))
                Text(text = "dd")
                Text(text = "dd")
            }


        }

    }

}


@Composable
fun BottomNavigationBar(modifier: Modifier=Modifier
){

    //하단 네비게이션바 컴포넌트 색 지정
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = Color(0xFF03dac5),
        selectedTextColor = Color(0xFF03dac5),
        indicatorColor = Color.Gray,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.White,
        disabledIconColor = Color.Cyan,
        disabledTextColor = Color.Cyan
    )

    val navigationBarItems : List<BottomNavItem> = listOf(
        BottomNavItem.HOME,
        BottomNavItem.EDIT,
        BottomNavItem.BUILD,
        BottomNavItem.FAVORITE,
        BottomNavItem.ACCOUNTBOX
    )

    var tabPage  by remember { mutableStateOf("home") }

    NavigationBar(
        modifier.fillMaxWidth(),
        containerColor = Color.Gray
    ) {
        navigationBarItems.forEach{ item->
            NavigationBarItem(
                selected = tabPage == item.title,
                onClick = { tabPage = item.title },
                icon = { Icon(imageVector = item.icon, contentDescription = item.screenRoute) },
                colors = colors,
                label = { if (tabPage == item.title) Text(text = item.screenRoute)}
            )
        }


  }
}

@Preview
@Composable
fun PreviewBottomNavigationBar(){
    val myViewModel = viewModel<MyViewModel>()
    BottomNavigationBar(modifier = Modifier)
    myViewModel.countFlow

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
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
               BottomNavigationBar(modifier = Modifier)
            },
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = { Text(text = "Top Bar") },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Gray),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable { })
                    },
                    actions = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search"
                        )
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Search"
                        )
                    }
                )

            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        isClicked = !isClicked
                        Log.d("floating",isClicked.toString())
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
                    Spacer(modifier = Modifier.padding(0.dp,0.dp,0.dp,2.5.dp))
                    LazyColumn {
                        items(list1) { item ->
                            RowItems(item = item)
                        }
                    }

                }

            }
        )
        if (!isClicked){
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 200.dp),
                contentAlignment = Alignment.BottomEnd,
                content = { Text(text = "String !! clicked !!")}
            )
        }

    }
}


