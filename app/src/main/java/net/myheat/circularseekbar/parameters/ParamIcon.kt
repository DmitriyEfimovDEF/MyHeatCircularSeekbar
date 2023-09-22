package net.myheat.circularseekbar.parameters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.myheat.circularseekbar.ui.theme.BlueLight
import net.myheat.circularseekbar.ui.theme.Border
import net.myheat.circularseekbar.ui.theme.CircularSeekbarTheme
import net.myheat.circularseekbar.ui.theme.IconBackground
import net.myheat.circularseekbar.ui.theme.SmallRoundedShape
import net.myheat.circularseekbar.ui.theme.TextPrimaryInverse

@Composable
fun ParamIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(36.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(IconBackground, IconBackground.copy(alpha = 0f)),
                    ),
                    shape = SmallRoundedShape,
                )
                .border(
                    width = 1.dp, brush = Brush.horizontalGradient(
                        colors = listOf(IconBackground.copy(alpha = 0f), IconBackground),
                    ), shape = SmallRoundedShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Icon image",
                modifier = Modifier
                    .size(24.dp)

                    .fillMaxSize(),
                tint = TextPrimaryInverse
            )
        }
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = BlueLight,
                    shape = SmallRoundedShape,
                )
                .border(
                    width = 1.dp,
                    color = Border,
                    shape = SmallRoundedShape,
                ),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ParamIconPreview() {
    CircularSeekbarTheme {
        ParamIcon()
    }
}