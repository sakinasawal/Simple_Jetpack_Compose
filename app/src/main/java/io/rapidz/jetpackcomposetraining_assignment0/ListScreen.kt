package io.rapidz.jetpackcomposetraining_assignment0

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.*
import kotlin.math.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.material.card.MaterialCardView

@Composable
fun ListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "List",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 20.dp)
        )

        val chipItems = listOf("Random", "Ascending Order", "Descending Order",
            "Number Only", "Word Only")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (chipText in chipItems) {
                Chip(
                    text = chipText,
                    onClick = {}
                )
            }
        }

        val textList = listOf(
            "9384", "Android", "Good", "To", "3032", "Image", "9091", "Programming",
            "Coding", "1298", "9947", "8732", "iOS", "Mobile"
        )

        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            items(textList) { text ->
                MaterialCardView(
                    modifier = Modifier.padding(vertical = 8.dp),
                    cardColor = MaterialCardViewBgColor,
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun Chip(text: String, isSelected: Boolean = false, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(50),
        elevation = 1.dp,
        modifier = Modifier.clickable { onClick() },
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = if (isSelected) MaterialTheme.colors.onPrimary
            else MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}


@Composable
fun MaterialCardView(
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        backgroundColor = cardColor,
        elevation = 4.dp,
        content = content
    )
}



