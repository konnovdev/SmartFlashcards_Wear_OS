package dev.konnov.smartflashcards.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import dev.konnov.smartflashcards.app.theme.SmartFlashcardsTheme

@Composable
fun CardFrontScreen(
    word: String,
    nextClicked: () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .align(Alignment.Center),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                word,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Box(
            modifier = Modifier
                .offset(y = 150.dp)
                .clip(RoundedCornerShape(50))
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .height(200.dp)
                .width(200.dp)
                .clickable { nextClicked() },
            contentAlignment = Alignment.TopCenter
        ) {
            Text("→", color = Color.Black, fontSize = 24.sp)
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun CardFrontScreenPreview() {
    SmartFlashcardsTheme {
        CardFrontScreen("Stagnation", {})
    }
}