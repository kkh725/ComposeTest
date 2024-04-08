package com.example.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
            Button(onClick = { navController.navigate("screen2") }){}
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

    @Preview
    @Composable
    fun prevGreeting() {
        Page1(rememberNavController())
    }
