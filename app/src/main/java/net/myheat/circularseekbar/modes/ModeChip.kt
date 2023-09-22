package net.myheat.circularseekbar.modes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.myheat.circularseekbar.ui.theme.CircularSeekbarTheme

@Composable
fun ModeChip(
    isSelected: Boolean,
    name: String,
    onSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(30.dp)
    val selected = rememberSaveable { mutableStateOf(isSelected) }
    val backgroundColor = remember(selected) {
        if (selected.value) Color(0xFF20374B) else Color(0xFFFFFFFF)
    }
    val borderColor = remember(selected) {
        if (selected.value) Color(0xFF20374B) else Color(0xFFF2F2F3)
    }
    val textColor = remember(selected) {
        if (selected.value) Color(0xFFFFFFFF) else Color(0xFF20374B)
    }
    Box(
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = shape)
            .background(color = backgroundColor, shape = shape)
            .clip(shape = shape)
            .clickable { onSelected(!selected.value) }
    ) {
        Text(
            text = name,
            color = textColor,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
private fun ChipsPreview() {
    CircularSeekbarTheme {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ModeChip(isSelected = false, name = "Unselected", onSelected = {})
            ModeChip(isSelected = true, name = "Selected", onSelected = {})
            ModeChip(isSelected = false, name = "Unselected", onSelected = {})
        }
    }
}