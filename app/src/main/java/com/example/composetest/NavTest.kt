package com.example.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                    Greeting("kkh3", modifier = Modifier, navController)
                }
            }

        }
    }


    @Composable
    fun Page1(navController: NavController) {
        Column(modifier = Modifier.padding(top = 24.dp)) {
            Button(onClick = { navController.navigate("screen2") }) {
                Text(text = "btn1")
            }
            Text(text = "hihi")

        }
    }

    @Composable
    fun Page2(navController: NavController) {
        Column {
            Text(text = "hihi2")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "btn1")
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

    @Preview
    @Composable
    fun prevGreeting() {
        Page1(rememberNavController())
    }
}