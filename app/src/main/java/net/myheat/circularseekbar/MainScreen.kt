package net.myheat.circularseekbar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.myheat.circularseekbar.parameters.ParameterViewState
import net.myheat.circularseekbar.seekbar.TabItems
import net.myheat.circularseekbar.seekbar.TabLayout
import net.myheat.circularseekbar.seekbar.TemperatureSeekbar
import net.myheat.circularseekbar.ui.theme.CircularSeekbarTheme
import net.myheat.circularseekbar.ui.theme.MediumRoundedShape
import net.myheat.circularseekbar.ui.theme.TextPrimary
import net.myheat.circularseekbar.ui.theme.TextPrimaryInactive
import net.myheat.circularseekbar.ui.theme.Typography

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .scrollable(
                state = rememberScrollableState { it }, orientation = Orientation.Vertical
            ),
    ) {
        Text(
            text = "Текущее состояние",
            style = Typography.headlineSmall,
            color = TextPrimaryInactive
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 8.dp)
        )
        Text(
            text = "Управление по температуре помещения",
            style = Typography.titleSmall,
            color = TextPrimary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 8.dp)
        )

        // контейнер для виджета с котлом
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), contentAlignment = Alignment.BottomStart) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 35.dp)
                    .background(color = TextPrimary, shape = MediumRoundedShape)
                    .clip(MediumRoundedShape),
                contentAlignment = Alignment.CenterEnd
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(vertical = 22.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ParameterViewState()
                    ParameterViewState()
                }
            }
            Box(modifier = Modifier.padding(start = 8.dp, bottom = 20.dp)) {
                Box(
                    modifier = Modifier
                        .size(width = 80.dp, height = 126.dp)
                        .background(color = Color.Gray)
                        .padding()
                ) {
//                Image(painter =, contentDescription =)

                }
            }

        }

        Text(
            text = "Выберите режим",
            style = Typography.headlineSmall,
            color = TextPrimaryInactive,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        TabLayout(tabItems = TabItems) {}
        TemperatureSeekbar()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CircularSeekbarTheme {
        MainScreen()
    }
}