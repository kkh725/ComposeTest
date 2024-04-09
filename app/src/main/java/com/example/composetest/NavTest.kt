package com.example.composetest

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class NavTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "screen1") {
                composable("screen1") {
                    Page1(navController)
                }
                composable("screen2") {
                    Page1(navController)

                }
                composable("screen3") {
                }
            }

        }
    }
}

    @Composable
    fun Page1(navController: NavController) {
            Button(onClick = {
                navController.navigate("screen2")
            }){}
    }


    @Composable
    fun Page2(navController: NavController,viewmodel:MyViewModel = viewModel<MyViewModel>()) {
        Column {
            Text(text = "hihi2")
            Button(onClick = { viewmodel.updateData("hi") }) {
                Text(text = viewmodel.myData.value)
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "btn1")
            }
        }
    }

    @Composable
    fun Page3(navController: NavController) {
        Column {
            Text(text = "hihi3")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "btn1")
            }
        }
    }

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun PrevGreeting() {
        val viewModel = viewModel<MyViewModel>()
        val countState = viewModel.countFlow.collectAsState(initial = 0)
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)){
            Page1(rememberNavController())
            Text(text = "${countState.value}", fontSize = 70.sp, modifier = Modifier.align(Alignment.Center))
        }
    Column {
        TextField(value = viewModel.myData.value, onValueChange = { newValue ->
            // 값을 변경할 때마다 State 업데이트
            viewModel.updateData(newValue)

        })
        Text(text = viewModel.myData.value)
    }

}
