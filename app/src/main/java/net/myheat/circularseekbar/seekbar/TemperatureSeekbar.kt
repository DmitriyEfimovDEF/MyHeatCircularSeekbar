package net.myheat.circularseekbar.seekbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.myheat.circularseekbar.CircularSeekbarView
import net.myheat.circularseekbar.FillerCircleView
import net.myheat.circularseekbar.ui.theme.Black
import net.myheat.circularseekbar.ui.theme.BlueLight
import net.myheat.circularseekbar.ui.theme.CircularSeekbarTheme
import net.myheat.circularseekbar.ui.theme.Grey
import net.myheat.circularseekbar.ui.theme.TickColor

@Composable
fun TemperatureSeekbar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(color = Color.Transparent),
        verticalArrangement = Arrangement.Center,
    ) {
        var progress by rememberSaveable { mutableFloatStateOf(0f) }

        Box(
            Modifier.padding(all = 16.dp)
        ) {
            CircularSeekbarView(
                value = progress, onChange = { v -> progress = v },
                modifier = Modifier
                    .padding(24.dp),
                startAngle = -120f, fullAngle = 240f,
                lineRoundEnd = true,
                activeColor = Black,
                inactiveColor = Grey,
                dotColor = Black,
                lineWeight = 12.dp,
                dotRadius = 16.dp,
                drawInCircle = {},
            )
            FillerCircleView(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                ticksColor = TickColor,
                ringColor = BlueLight,
            )
            Text(
                text = "progress: ${progress.times(100).toInt()}",
                modifier = Modifier.align(Alignment.Center)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp, vertical = 20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleButtonShape)
                        .clickable {
                            progress = progress
                                .minus(0.1f)
                                .coerceIn(0.0f, 1f)
                        }
                        .background(
                            color = Grey,
                            shape = CircleButtonShape
                        ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Remove icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
                Box(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleButtonShape)
                        .clickable {
                            progress = progress
                                .plus(0.1f)
                                .coerceIn(0.0f, 1f)
                        }
                        .background(
                            color = Grey,
                            shape = CircleButtonShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CircularSeekbarTheme {
        TemperatureSeekbar()
    }
}

val CircleButtonShape = RoundedCornerShape(size = 58.dp)