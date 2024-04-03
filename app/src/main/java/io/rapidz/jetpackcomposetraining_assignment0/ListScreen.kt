package io.rapidz.jetpackcomposetraining_assignment0

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController

@Composable
fun ListScreen(navController: NavHostController) {

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
            modifier = Modifier.padding(top = 12.dp)
        )

        val chipItems = listOf("Random", "Ascending Order", "Descending Order",
            "Number Only", "Word Only")

        var selectedChip by remember { mutableStateOf<String?>(null) }

        val textList = listOf(
            "9384", "Android", "Good", "To", "3032", "Image", "9091", "Programming",
            "Coding", "1298", "9947", "8732", "iOS", "Mobile"
        )

        val displayedList = remember { mutableStateOf(textList) }

        ChipGroup(
            chipItems = chipItems,
            selectedChip = selectedChip,
            onChipSelected = { chipText ->
                selectedChip = chipText
                displayedList.value = when (chipText) {
                    "Random" -> textList.shuffled()
                    "Ascending Order" -> textList.sorted()
                    "Descending Order" -> textList.sortedDescending()
                    "Number Only" -> textList.filter {
                        it.isDigitsOnly()
                    }
                    "Word Only" -> textList.filter {
                        it.all { c: Char ->  c.isLetter()}
                    }
                    else -> textList
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(displayedList.value) { text ->
                MaterialCardView(
                    modifier = Modifier.padding(vertical = 4.dp),
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .background(MaterialCardViewBgColor)
                            .padding(16.dp),
                        color = Color.White,
                    )
                }
            }
        }

        Button(onClick = {
            navController.navigate("image")
        },
            colors = ButtonDefaults.buttonColors(containerColor = OrangeCentre),
            shape = RoundedCornerShape(10)
            ) {
            Text(
                text = "Image",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    chipItems: List<String>,
    selectedChip: String?,
    onChipSelected: (String) -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        chipItems.forEach { chipText ->
            Chip(
                text = chipText,
                isSelected = chipText == selectedChip,
                onClick = { onChipSelected(chipText) }
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = if (isSelected) Color.Gray
        else Color.Black,
        modifier = Modifier
            .background(
                color = Color.LightGray,
                shape = CircleShape
            )
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp)
    )
}

@Composable
fun MaterialCardView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        content = content
    )
}



