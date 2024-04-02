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
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
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
@Composable
fun MyButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text)
    }
}
//컴포저블 함수 자체가 그냥 하나의 view혹은 레이아웃이라고 생각하면된다.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(name) }
    var state by remember { mutableStateOf(false) }
    var text2 by remember {
        mutableStateOf(name)
    }
    Column {
        Row {
            Text(text = text2, modifier.clickable { text2 = "rlahfld" },
                color = Color.Red, fontSize = 25.sp)

            Text(text = "hi ", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            Text(text = "hi")

        }
        Checkbox(checked = state, onCheckedChange = {
            state = it
        })
        MyButton(text = "text") {
            
        }
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "description",modifier.clickable { text2 = "equal" })
        Text(

            text = text,
        )

        Text(text = "Text")
        Button(onClick = { if (state){
            text = "true"
        }
        }) {
            Text(text = text)
        }
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() { //매게변수를 사용하지않아야한다.
    ComposeTestTheme {
        Greeting(name = "Android")
    }
}