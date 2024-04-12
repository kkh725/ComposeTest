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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NavTest @Inject constructor() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel : MyViewModel = viewModel()
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
    fun Page2(
        navController: NavController,
        viewmodel: MyViewModel = viewModel() //생명주기가 같아진다.
    ) {
        viewmodel.countFlow
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
        var isClicked by remember { mutableStateOf(false) }
        val viewModel = viewModel<MyViewModel>()
        val countState by viewModel.countFlow.collectAsState(initial = 0)

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)){
            Page1(rememberNavController())
            Text(text = countState.toString(), fontSize = 70.sp, modifier = Modifier.align(Alignment.Center))
        }

    Column {

        TextField(value = viewModel.myData.value, onValueChange = { newValue ->
            // 값을 변경할 때마다 State 업데이트
            viewModel.updateData(newValue)

        })
        Button(onClick = { isClicked = !isClicked }) {
            
        }
        if (isClicked)Text(text = viewModel.myData.value)
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun pre(){
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) {
        Column (modifier = Modifier.padding(it)){
            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp),
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Hide bottom sheet")
                    }
                    Text(text = "text2")
                    Text(text = "text2")
                    Text(text = "text2")
                }
            }
        }
        // Screen content


    }
}
