package net.myheat.circularseekbar.modes

import androidx.compose.runtime.Immutable

@Immutable
data class ModeChipViewData(
    val id: Int,
    val name: String,
    val isPressed: Boolean
)
