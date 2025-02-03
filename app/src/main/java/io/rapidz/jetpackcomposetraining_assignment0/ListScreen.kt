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

enum class Chip{
    RANDOM, ASCENDING_ORDER, DESCENDING_ORDER, NUMBER_ONLY, WORD_ONLY
}

data class ChipItems (
    val chipSorting: Chip ?= null,
    val name : String = ""
)



@Composable
fun ListScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing_20),
        verticalArrangement = Arrangement.spacedBy(spacing_20),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "List",
            style = TextStyle(fontSize = font_size_24, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = spacing_12)
        )

        var selectedChip by remember { mutableStateOf<Chip?>(null) }

        val textList = listOf(
            "9384", "Android", "Good", "To", "3032", "Image", "9091", "Programming",
            "Coding", "1298", "9947", "8732", "iOS", "Mobile"
        )

        val chipItem = listOf(ChipItems(Chip.RANDOM, "Random"),
            ChipItems(Chip.ASCENDING_ORDER, "Ascending Order"),
            ChipItems(Chip.DESCENDING_ORDER, "Descending Order"),
            ChipItems(Chip.NUMBER_ONLY, "Number Only"),
            ChipItems(Chip.WORD_ONLY, "Word Only")
            )

        val displayedList = remember { mutableStateOf(textList) }

        ChipGroup(
            chipItems = chipItem,
            selectedChip = selectedChip,
            onChipSelected = { chipText ->
                selectedChip = chipText
                displayedList.value = when (chipText) {
                     Chip.RANDOM-> textList.shuffled()
                     Chip.ASCENDING_ORDER -> textList.sorted()
                     Chip.DESCENDING_ORDER -> textList.sortedDescending()
                     Chip.NUMBER_ONLY -> textList.filter {
                        it.isDigitsOnly()
                    }
                    Chip.WORD_ONLY -> textList.filter {
                        it.all { c: Char ->  c.isLetter()}
                    }
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .weight(weight_1f)
        ) {
            items(displayedList.value) { text ->
                MaterialCardView(
                    modifier = Modifier.padding(vertical = spacing_4),
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .background(MaterialCardViewBgColor)
                            .padding(spacing_16),
                        color = Color.White,
                    )
                }
            }
        }

        Button(onClick = {
            navController.navigate("image")
        },
            colors = ButtonDefaults.buttonColors(containerColor = OrangeCentre),
            shape = RoundedCornerShape(percent_10)
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
    chipItems: List<ChipItems>,
    selectedChip: Chip?,
    onChipSelected: (Chip) -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(spacing_7),
        verticalArrangement = Arrangement.spacedBy(spacing_7),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing_16),
    ) {
        chipItems.forEach { chipText ->
            Chip(
                text = chipText.name,
                isSelected = chipText.chipSorting == selectedChip,
                onClick = { onChipSelected(chipText.chipSorting!!) }
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
            .padding(horizontal = spacing_8, vertical = spacing_8)
    )
}

@Composable
fun MaterialCardView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(percent_50),
        content = content
    )
}



