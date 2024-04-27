package com.example.composetest

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
//                        EditText()
//                        MyEditText(LocalContext.current)
                        test2()

                    }
                }
            }


        }
    }
}

@Composable
fun test(){
    Text(text = "String")
}

@Preview
@Composable
fun EditText(){
    val annotatedString2 = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
            append("Bold Red Text")
        }
        append("bold")}
    var isBold by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(TextFieldValue()) }


    Column {
        BasicTextField(
            modifier = Modifier
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        // 포커스를 얻었을 때 선택된 텍스트의 정보를 감지하여 출력합니다.
                        val selectionStart = textValue.selection.start
                        val selectionEnd = textValue.selection.end
                        Log.d("log.d","Selection start: $selectionStart, end: $selectionEnd")
                    }
                },
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
            },
            textStyle = if (isBold) {
                TextStyle.Default.copy(fontWeight = FontWeight.Bold)
            } else {
                TextStyle.Default.copy(brush = Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta, Color.Yellow))) // 기본 스타일 사용
            },

        )


        Button(onClick = { isBold = !isBold}) {
            Text(text = annotatedString2)
            println("selected text: {${textValue.text}}")
            println("selected text: {${textValue.getSelectedText()}}")

        }
        Button(onClick = { isBold = !isBold}) {
            Text(text = "bold")
            println("selected text: {$textValue}")
            println("selected text: {${textValue.getSelectedText()}}")

        }


    }
}
@Preview
@Composable
fun test2(){
    var textFieldValue = TextFieldValue(buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
            append("hi")
        }
    })
    var textValue by remember { mutableStateOf(TextFieldValue()) }

    Column {
        BasicTextField(

            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
            },

        )

        Button(onClick = {textValue = TextFieldValue(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                append(textValue.getSelectedText())
            }
        })}) {

            Text(text = textValue.text.plus(textValue.text))
            println("selected text: {$textValue}")
            println("selected text: {${textValue.getSelectedText()}}")

        }
    }

}














