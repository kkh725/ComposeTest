package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
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
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf(name)
    }
    Column {
        Row {
            Text(text = "hi ")
            Text(text = "hi ")
            Text(text = "hi")
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