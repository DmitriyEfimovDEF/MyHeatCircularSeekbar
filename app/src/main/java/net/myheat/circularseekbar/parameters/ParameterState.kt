package net.myheat.circularseekbar.parameters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.myheat.circularseekbar.ui.theme.CircularSeekbarTheme
import net.myheat.circularseekbar.ui.theme.IconBackground
import net.myheat.circularseekbar.ui.theme.TextPrimaryInverse
import net.myheat.circularseekbar.ui.theme.TextSecondaryInverse
import net.myheat.circularseekbar.ui.theme.Typography

@Composable
fun ParameterViewState() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(IntrinsicSize.Max)
    ) {
        ParamIcon()
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = "asdf asdf ",
                style = Typography.headlineMedium,
                color = TextPrimaryInverse,
                modifier = Modifier.widthIn(min = 40.dp, max = 88.dp)
            )
            Text(
                text = "asdf asdf ",
                style = Typography.labelSmall,
                color = TextSecondaryInverse,
                modifier = Modifier.width(IntrinsicSize.Max)
            )
        }
        Divider(
            color = IconBackground,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            text = "asdf asdf ",
            style = Typography.headlineMedium,
            color = TextPrimaryInverse,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(10.dp)
                .widthIn(10.dp, 50.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0L)
@Composable
fun ParameterStatePreview() {
    CircularSeekbarTheme {
        ParameterViewState()
    }
}