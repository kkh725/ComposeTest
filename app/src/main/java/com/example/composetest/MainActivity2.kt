package com.example.composetest

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        EditText()
                    }
                }
            }


        }
    }
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
    var isChecked by remember { mutableStateOf(false) }


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
        Switch2()
        Switch(checked = true, onCheckedChange = {})




    }
}
@Composable
fun MySwitchWithIcon(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val icon = if (checked) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Switch(
        modifier = Modifier
            .scale(2f)
            .padding(20.dp),
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = {
            Icon(icon, contentDescription = "Switch Icon")
        }
    )
}

@Composable
fun Switch2(
    scale: Float = 2f,
    width: Dp = 40.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = Color.Red,
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    val switchON = remember {
        mutableStateOf(true) // Initially the switch is ON
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() },
        label = ""
    )

    Canvas(
        modifier = Modifier
            .padding(start = (width.value / 1.85).dp, top = (height.value / 1.3).dp)
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                    }
                )
            }
    ) {
        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            ),
            style = Fill //Fill / Stroke

        )
    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(text = if (switchON.value) "ON" else "OFF")
}

















