package io.rapidz.jetpackcomposetraining_assignment0

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.*
import kotlin.math.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LogoImage(modifier = Modifier.size(200.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.hello_world),
                style = TextStyle(fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }

    LaunchedEffect(Unit) {
        // Delay for 3 seconds
        delay(3000)

        // Navigate to the login screen
        navController.navigate("login")
    }
}

@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2

        // Draw the inner circle with gradient
        drawLinearGradientCircle(
            colors = listOf(OrangeStart, OrangeCentre, OrangeEnd),
            radius = radius * 0.7f,
            angle = 320f
        )

        // Draw the border circle
        drawCircle(
            color = OrangeBorder,
            radius = radius * 0.7f,
            style = Stroke(width = 4.dp.toPx())
        )

    }
}

// draw gradient with angle with complex calculation
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

