package net.myheat.circularseekbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun FillerCircleView(
    modifier: Modifier = Modifier,
    ticksColor: Color = Color.Blue,
    ringColor: Color = Color.Blue,
) {
    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    Box(modifier = modifier) {
        Canvas(modifier = modifier) {
            val width = size.width
            val height = size.height
            val circleThickness = width.times(other = 0.04f)
            circleCenter = Offset(x = width / 2f, y = height / 2f)

            drawCircle(
                style = Stroke(
                    width = circleThickness.times(other = 1.5f)
                ),
                color = ringColor,
                radius = width.times(other = 0.3f),
                center = circleCenter
            )

            val outerRadius = width.times(other = 0.36f)
            val gap = width.times(other = 0.12f).unaryMinus()

            for (i in 0..FullCircle.toInt() step 6) {
                drawTick(
                    i = i,
                    gap = gap,
                    outerRadius = outerRadius.minus(16),
                    circleCenter = circleCenter,
                    circleThickness = circleThickness.times(other = 0.5f),
                    primaryColor = ticksColor
                )
            }
            for (i in 0..FullCircle.toInt() step 30) {
                drawTick(
                    i = i,
                    gap = gap,
                    outerRadius = outerRadius.minus(circleThickness.times(other = 0.2f)).minus(16),
                    circleCenter = circleCenter,
                    circleThickness = circleThickness.times(other = 0.2f),
                    primaryColor = ticksColor
                )
            }
        }
    }
}

private fun DrawScope.drawTick(
    i: Int,
    gap: Float,
    outerRadius: Float,
    circleCenter: Offset,
    circleThickness: Float,
    primaryColor: Color
) {

    val angleInDegrees = (i * FullCircle) / FullCircle
    val angleInRad = ((angleInDegrees * PI) / HalfCircle) + (PI / 2f)

    val yGapAdjustment = cos((angleInDegrees * PI) / HalfCircle) * gap
    val xGapAdjustment = -sin(angleInDegrees * PI / HalfCircle) * gap

    val start = Offset(
        x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
        y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
    )

    val end = Offset(
        x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
        y = (outerRadius * sin(angleInRad) + circleThickness + circleCenter.y + yGapAdjustment).toFloat()
    )

    rotate(angleInDegrees, pivot = start) {
        drawLine(color = primaryColor, start = start, end = end, strokeWidth = 1.dp.toPx())
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    FillerCircleView(
        modifier = Modifier.size(600.dp)
    )
}

private const val HalfCircle = 180f
private const val FullCircle = 360f
