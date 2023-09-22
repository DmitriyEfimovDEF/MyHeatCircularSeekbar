package net.myheat.circularseekbar.seekbar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.myheat.circularseekbar.ui.theme.CircularSeekbarTheme
import net.myheat.circularseekbar.ui.theme.Typography

@Composable
fun TabLayout(
    tabItems: List<String>,
    content: @Composable () -> Unit
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    Column {
        Box(contentAlignment = Alignment.BottomStart) {
            Divider(
                thickness = 1.dp,
                color = Color(0xFFF2F2F3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp)
            )
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                edgePadding = 0.dp,
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier
                            .customTabIndicatorOffset(
                                currentTabPosition = tabPositions[tabIndex],
                                tabWidth = 26,
                                tabOffset = 16
                            ),
                        height = 3.dp,
                        width = 26.dp,
                        color = Color(0xFF20374B),
                        shape = RoundedCornerShape(0.dp)
                    )
                },
                divider = {},
            ) {
                tabItems.forEachIndexed { index, item ->
                    val isSelected = tabIndex == index
                    Tab(
                        selected = isSelected,
                        onClick = { tabIndex = index },
                        selectedContentColor = Color.Transparent,
                        unselectedContentColor = Color.Transparent,
                        text = {
                            Box(
                                modifier = Modifier
                                    .height((Typography.labelMedium.lineHeight.value).times(other = 2.3).dp),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                Text(
                                    text = item.uppercase(),
                                    style = Typography.labelMedium,
                                    color = if (isSelected) Color(0xFF20374B) else Color(0xFFBDBDBD),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
        content()
    }
}

private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Int,
    tabOffset: Int,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth.dp,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "currentTabWidth"
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left.plus(tabOffset.dp),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "indicatorOffset"
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@Preview
@Composable
private fun TabsLayoutPreview() {
    CircularSeekbarTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            TabLayout(
                tabItems = TabItems
            ) {}
        }
    }
}

val TabItems = listOf(
    "Температура \nПомещения",
    "Горячая вода"
)