package net.myheat.circularseekbar

import android.content.res.Resources.getSystem
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.RequestDisallowInterceptTouchEvent
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sin

@Suppress("LongParameterList", "CyclomaticComplexMethod", "LongMethod", "FunctionNaming")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CircularSeekbarView(
    value: Float,
    onChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    startAngle: Float = 0f,
    fullAngle: Float = FullCircle.toFloat(),
    activeColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    inactiveColor: Color = MaterialTheme.colorScheme.primaryContainer,
    dotColor: Color = MaterialTheme.colorScheme.primary,
    lineWeight: Dp = 20.dp,
    lineRoundEnd: Boolean = false,
    dotRadius: Dp = 20.dp,
    dotTouchThreshold: Dp = 15.dp,
    drawInCircle: DrawScope.() -> Unit = {},
    drawDot: (DrawScope.(Offset, Float, Color, Float) -> Unit)? = null
) {
    val dpScale = getSystem().displayMetrics.density
    val lineWeightInPx = dpScale * lineWeight.value
    val dotRadiusInPx = dpScale * dotRadius.value
    val dotTouchThresholdInPx = dpScale * dotTouchThreshold.value
    val innerStartAngle = startAngle - QuarterCircle.toFloat()
    val stroke = Stroke(lineWeightInPx, cap = if (lineRoundEnd) StrokeCap.Round else StrokeCap.Butt)
    var sweepAngle = value * fullAngle
    if (steps > 0) {
        val perStep = fullAngle / steps
        sweepAngle = (sweepAngle / perStep).roundToInt() * perStep
    }

    var dragCenter by remember { mutableStateOf<Offset?>(null) }
    var arcCenter by remember { mutableStateOf<Offset?>(null) }
    var draggingPointerId by remember { mutableStateOf<Int?>(null) }
    val requestDisallowInterceptTouchEvent = remember { RequestDisallowInterceptTouchEvent() }

    Canvas(modifier = modifier
        .fillMinDimension()
        .pointerInteropFilter(requestDisallowInterceptTouchEvent) {
            when (it.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    val center = dragCenter ?: return@pointerInteropFilter false
                    if (Offset(it.getX(it.actionIndex), it.getY(it.actionIndex))
                            .minus(center)
                            .getDistance() <= dotRadiusInPx + dotTouchThresholdInPx
                    ) {
                        draggingPointerId = it.getPointerId(it.actionIndex)
                        requestDisallowInterceptTouchEvent(true)
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    val center = arcCenter ?: return@pointerInteropFilter false
                    val pointerId = draggingPointerId ?: return@pointerInteropFilter true
                    val pointerIndex = it.findPointerIndex(pointerId)
                    val pointerOffset = Offset(it.getX(pointerIndex), it.getY(pointerIndex))
                    val v = pointerOffset.minus(center)
                    if (v.getDistance() == 0f) return@pointerInteropFilter true
                    var nextSweepAngle =
                        (atan(v.y / v.x) / PI * HalfCircle - innerStartAngle).toFloat()

                    if (v.x < 0) {
                        nextSweepAngle += HalfCircle
                    }

                    while (nextSweepAngle < 0) {
                        nextSweepAngle += FullCircle
                    }

                    while (nextSweepAngle > FullCircle) {
                        nextSweepAngle -= FullCircle
                    }

                    nextSweepAngle = when {
                        (sweepAngle > fullAngle.times(other = 0.75f))
                                && (nextSweepAngle < fullAngle.div(other = 2) || (nextSweepAngle > fullAngle)) ->
                            fullAngle

                        (sweepAngle < fullAngle.times(other = 0.25f) && nextSweepAngle > (fullAngle / 2)) -> 0f

                        else -> nextSweepAngle
                    }
                    if (steps > 0) {
                        val perStep = fullAngle / steps
                        val step = (nextSweepAngle / perStep).roundToInt()
                        nextSweepAngle = step * perStep
                    }
                    if (nextSweepAngle / fullAngle != value) {
                        onChange(nextSweepAngle / fullAngle)
                    }
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                    if (it.getPointerId(it.actionIndex) == draggingPointerId) {
                        draggingPointerId = null
                        requestDisallowInterceptTouchEvent(false)
                    }
                }

                MotionEvent.ACTION_CANCEL -> draggingPointerId = null

                else -> return@pointerInteropFilter false
            }
            true
        }) {

        val radius = size.minDimension / 2.0f - max(lineWeightInPx / 2, dotRadiusInPx)

        drawArc(
            color = inactiveColor,
            startAngle = innerStartAngle + sweepAngle,
            sweepAngle = fullAngle - sweepAngle,
            useCenter = false,
            topLeft = Offset(center.x - radius, center.y - radius),
            size = Size(radius * 2, radius * 2),
            style = stroke
        )

        drawArc(
            color = activeColor,
            startAngle = innerStartAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(center.x - radius, center.y - radius),
            size = Size(radius * 2, radius * 2),
            style = stroke
        )

        val dotAngle = (innerStartAngle + sweepAngle)
        val dotCenter = Offset(
            center.x + cos((dotAngle / HalfCircle.toFloat() * PI).toFloat()) * radius,
            center.y + sin((dotAngle / HalfCircle.toFloat() * PI).toFloat()) * radius
        )
        this.drawInCircle()
        if (drawDot != null) {
            this.drawDot(dotCenter, dotAngle, dotColor, dotRadiusInPx)
        } else {
            drawCircle(
                color = dotColor,
                center = dotCenter,
                radius = dotRadiusInPx
            )
        }

        dragCenter = dotCenter
        arcCenter = center

    }
}

@Preview(showBackground = true)
@Composable
fun CircularSeekbarPreview() {
    CircularSeekbarView(
        value = 0.35f, onChange = {}
    )
}

private const val FullCircle = 360
private const val HalfCircle = 180
private const val QuarterCircle = 90