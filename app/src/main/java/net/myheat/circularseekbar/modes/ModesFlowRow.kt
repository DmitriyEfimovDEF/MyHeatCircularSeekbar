package net.myheat.circularseekbar.modes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ModesFlowRow(modes: ImmutableList<ModeChipViewData>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        modes.forEach { vd ->
            ModeChip(isSelected = vd.isPressed, name = vd.name, onSelected = {})
        }
    }
}


private val Modes = persistentListOf(
    ModeChipViewData(id = 0, isPressed = false, name = "Short"),
    ModeChipViewData(id = 0, isPressed = true, name = "Short"),
    ModeChipViewData(id = 0, isPressed = false, name = "Unselected"),
    ModeChipViewData(id = 0, isPressed = false, name = "Short"),
    ModeChipViewData(id = 0, isPressed = true, name = "LongLong"),
    ModeChipViewData(id = 0, isPressed = false, name = "Unselected"),
    ModeChipViewData(id = 0, isPressed = false, name = "Unselected"),
    ModeChipViewData(id = 0, isPressed = true, name = "Selected"),
    ModeChipViewData(id = 0, isPressed = false, name = "Unselected"),
)