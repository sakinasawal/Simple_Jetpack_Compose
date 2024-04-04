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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        GradientCircle(
            modifier = Modifier.size(200.dp),
            colors = listOf(OrangeStart, OrangeCentre, OrangeEnd),
            radius = 100f
        )

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(id = R.string.hello_world),
            style = TextStyle(fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
        )
    }

    LaunchedEffect(Unit) {
        // Delay for 3 seconds
        delay(3000)

        // Navigate to the login screen
        navController.navigate("login")
    }
}

@Composable
fun GradientCircle(modifier: Modifier = Modifier,
                   colors: List<Color>,
                   radius: Float,
                   borderWidth: Dp = 4.dp
) {
    val borderWidthPx = with(LocalDensity.current) { borderWidth.toPx() }

    Canvas(modifier = modifier.size(radius.dp * 2)) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val gradientRadius = size.width / 2f
        val gradientAngle = 230f

        rotate(-gradientAngle, pivot = Offset(centerX, centerY)) {
            val gradient = Brush.linearGradient(
                colors = colors,
                start = Offset(centerX - gradientRadius, centerY),
                end = Offset(centerX + gradientRadius, centerY)
            )

            drawCircle(brush = gradient, radius = gradientRadius)
        }

        drawCircle(
            brush = Brush.linearGradient(listOf(OrangeBorder, OrangeBorder)),
            style = Stroke(width = borderWidthPx),
            radius = gradientRadius
        )
    }
}

