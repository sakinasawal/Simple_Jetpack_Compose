package io.rapidz.jetpackcomposetraining_assignment0

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


@Composable
fun ImageScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
    ) {

        val (text, earthImage, helloWorldImage, starButton) = createRefs()
        val context = LocalContext.current

        Text(
            text = "Hello, World",
            style = TextStyle(fontSize = 30.sp, color = HelloWorldColor),
            modifier = Modifier
                .constrainAs(text) {
                    centerHorizontallyTo(parent)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.hello_world),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(helloWorldImage) {
                    top.linkTo(text.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(starButton.top)
                })

        Image(
            painter = painterResource(id = R.drawable.ic_world),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(earthImage){
                    top.linkTo(text.bottom)
                    start.linkTo(helloWorldImage.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(starButton.top)
                    width = Dimension.fillToConstraints
        })

        Image(
            painter = painterResource(android.R.drawable.btn_star_big_on),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .background(Color.LightGray)
                .constrainAs(starButton){
                    top.linkTo(helloWorldImage.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .clickable {
                    Toast.makeText(context, "Thanks for the rating!", Toast.LENGTH_SHORT).show()
                }
        )


    }

}




