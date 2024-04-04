package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Navi : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "screen1") {
                composable("screen1") {
                    Greeting("kkh1", modifier = Modifier,navController)
                }
                composable("screen2") {
                    Greeting("kkh2", modifier = Modifier,navController)
                }
                composable("screen3") {
                    Greeting("kkh3", modifier = Modifier,navController)
                }
            }

        }
    }
}

@Composable
fun Page1(navController: NavController){
    Column {
        Text(text = "hihi")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "btn1")
        }
    }
}

@Composable
fun Page2(navController: NavController){
    Column {
        Text(text = "hihi2")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "btn1")
        }
    }
}

@Composable
fun Page3(navController: NavController){
    Column {
        Text(text = "hihi3")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "btn1")
        }
    }
}