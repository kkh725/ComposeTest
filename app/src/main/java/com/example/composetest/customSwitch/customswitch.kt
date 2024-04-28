package com.example.composetest.customSwitch

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BasicSwitch(
    scale: Float = 2f,
    width: Dp = 40.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = Color.Red,
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp,
    onCheckedChanged: (checked: Boolean) -> Unit)

{

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
                        if ( switchON.value){
                            onCheckedChanged(true)
                        }
                        else onCheckedChanged(false)
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



}
@Preview
@Composable
fun PrevBasicSwitch(){
    Box(modifier = Modifier.size(100.dp)) {

        BasicSwitch(onCheckedChanged = { it-> if (it)print("hello") })
    }

}
