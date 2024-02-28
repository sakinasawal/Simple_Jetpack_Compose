package io.rapidz.jetpackcomposetraining_assignment0

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

@Composable
fun SplashScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF026C89))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LogoImage(modifier = Modifier.size(200.dp))

        Text(
            text = "Hello World!",
            style = TextStyle(fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color(0xFF026C89))
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        LogoImage(modifier = Modifier.size(200.dp))
//
//        Spacer(modifier = Modifier.height(300.dp)) // Add some spacing between logo and text
//
//        Text(
//            text = "Hello World!",
//            style = TextStyle(fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
//        )
//    }
}

@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2

        // Draw the inner circle with gradient
        drawLinearGradientCircle(
            colors = listOf(Color(0xFFFC6815), Color(0xFFFA8611), Color(0xFFDA2600)),
            radius = radius * 0.7f,
            angle = 320f
        )

        // Draw the border circle
        drawCircle(
            color = Color(0xFFFFBF00),
            radius = radius * 0.7f,
            style = Stroke(width = 4.dp.toPx())
        )

    }
}

fun DrawScope.drawLinearGradientCircle(
    colors: List<Color>,
    radius: Float,
    angle: Float
) {
    val centerX = size.width / 2
    val centerY = size.height / 2

    val startX = centerX + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
    val startY = centerY + radius * sin(Math.toRadians(angle.toDouble())).toFloat()

    val endX = centerX - radius * cos(Math.toRadians(angle.toDouble())).toFloat()
    val endY = centerY - radius * sin(Math.toRadians(angle.toDouble())).toFloat()

    drawCircle(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startX, startY),
            end = Offset(endX, endY)
        ),
        radius = radius
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

