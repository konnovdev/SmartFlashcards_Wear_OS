package dev.konnov.smartflashcards.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.google.android.horologist.compose.navscaffold.scrollableColumn
import dev.konnov.smartflashcards.app.theme.SmartFlashcardsTheme

@Composable
fun CardBackScreen(
    lazyListState: ScalingLazyListState,
    definition: String,
    correctClicked: () -> Unit,
    incorrectClicked: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    Box(Modifier.fillMaxSize()) {
        ScalingLazyColumn(
            modifier = Modifier.scrollableColumn(focusRequester, lazyListState),
            state = lazyListState,
            autoCentering = null
        ) {
            item {
                Text(
                    definition,
                    Modifier
                        .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 56.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = (-50).dp, y = 100.dp)
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .height(150.dp)
                .width(150.dp)
                .clickable { correctClicked() },
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                "✔",
                Modifier.offset(x = 28.dp, y = 12.dp),
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier
                .offset(x = 50.dp, y = 100.dp)
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .height(150.dp)
                .width(150.dp)
                .clickable { incorrectClicked() },
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                "x",
                Modifier.offset(x = ((-28).dp), y = 12.dp),
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun CardBackScreenPreview() {
    SmartFlashcardsTheme {
        CardBackScreen(
            ScalingLazyListState(),
            "делать решительный шаг\n" +
                    "\n" +
                    "commit oneself to a course of action about which one is nervous.\n" +
                    "\n" +
                    "\"she wondered whether to enter for the race, but decided to take the plunge\"\n" +
                    "\"They're finally taking the plunge and getting married.\"\n" +
                    "\n" +
                    "\n" +
                    "plunge:\n" +
                    "jump or dive quickly and energetically.\n" +
                    "\"our daughters whooped as they plunged into the sea\"\n",
            {},
            {})
    }
}
