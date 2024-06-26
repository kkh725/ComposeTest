package com.example.composetest

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetest.googleLogin.GoogleLoginSuccessDialog
import com.example.composetest.googleLogin.GoogleLoginViewModel
import com.example.composetest.ui.theme.ComposeTestTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //의존성주입
class MainActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: GoogleLoginViewModel by viewModels()

        super.onCreate(savedInstanceState)
        setContent {

            //파이어베이스 로그아웃
            Firebase.auth.signOut()
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "screen1") {
                composable("screen1") {
                    GoogleLoginBtn(viewModel,navController)
                }
                composable("screen2") {
                    GreetingPreview(navController)
                }
                composable("screen3") {
                    GreetingPreview(navController)

                }
            }
        }
    }
}



@Preview
@Composable
fun Previtemtest() {

    var isImageClicked by remember { mutableStateOf(false) }
    //card에 이미지와 버튼을 넣어서 리사이클러뷰를 만드는코드


    Column {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(500.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            GlideImage(
                imageModel = "https://i.namu.wiki/i/oDFMOHx4sv7TYNQbzqu8eg2QC9Rk5KVUGqDxZEJWrthOuIRA019gsMeK1gdMpQeNOe7bXtHbne-" +
                        "lrLZl5PDuyjRiEhHnNyxlXF-SptUDAhWNi-S-k9FxWEWTdRnruXs1z5lFAMETAUyhsF50KF24TQ.webp",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                circularReveal = CircularReveal(duration = 250),

            )
        }
        Text(text = "string")
        Icon(
            modifier = Modifier.clickable { isImageClicked = !isImageClicked },
            imageVector = if (isImageClicked) Icons.Default.Search else Icons.Default.Settings,
            contentDescription = null,
            tint = Color.Black
        )

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
                GlideImage(imageModel = "https://i.namu.wiki/i/oDFMOHx4sv7TYNQbzqu8eg2QC9Rk5KVUGqDxZEJWrthOuIRA019gsMeK1gdMpQeNOe7bXtHbne-lrLZl5PDuyjRiEhHnNyxlXF-SptUDAhWNi-S-k9FxWEWTdRnruXs1z5lFAMETAUyhsF50KF24TQ.webp",)

                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
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

@Composable
fun GoogleLoginBtn(viewModel: GoogleLoginViewModel, navController: NavController) {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        viewModel.handleGoogleSignInResult(result.data, navController)
    }

    //버튼 클릭시 구글 로그인 시도
    Button(
        onClick = {
            viewModel.signInWithGoogle(launcher,context)
        }
    ) {
        Text(text = "Google Sign In")
    }
    if (viewModel.state.value) {
        GoogleLoginSuccessDialog(viewModel.state)
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GreetingPreview(navController: NavController) {

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
                    navigationIcon = {

                        Icon(
                            imageVector = Icons.Default.Add,
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
                    Icon(Icons.Default.Search, contentDescription = "Add")
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {

                    Spacer(modifier = Modifier.padding(0.dp,0.dp,0.dp,3.5.dp))
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


