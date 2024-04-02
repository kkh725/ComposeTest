package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
//컴포저블 함수 자체가 그냥 하나의 view혹은 레이아웃이라고 생각하면된다.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf(name)
    }
    Column {
        modifier.padding(16.dp)
        Row {
            Text(text = "hi ", color = Color.Red, fontSize = 25.sp)
            Text(text = "hi ", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            Text(text = "hi",modifier.padding(start = 10.dp, top = 45.dp))
        }
        Text(
            text = text,
            modifier = modifier
        )
        Text(text = "Text")
        Button(onClick = { text = "hello" }) {
            Text(text = text)
        }
    }

}
@Composable
fun GreetingScreen() {
    Scaffold(
        // Scaffold 설정
    ) {
        Greeting(name = "Your Name")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() { //매게변수를 사용하지않아야한다.
    ComposeTestTheme {
        GreetingScreen()
    }
}