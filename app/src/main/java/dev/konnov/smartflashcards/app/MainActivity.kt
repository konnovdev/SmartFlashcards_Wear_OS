package dev.konnov.smartflashcards.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*
import dev.konnov.smartflashcards.app.theme.SmartFlashcardsTheme
import dev.konnov.smartflashcards.app.ui.PlayFlashcardsMock

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp(modifier: Modifier = Modifier) {
    SmartFlashcardsTheme {
        val scalingLazyState = ScalingLazyListState()

        Scaffold(
            modifier = modifier,
            positionIndicator = { PositionIndicator(scalingLazyListState = scalingLazyState) }
        ) {
            PlayFlashcardsMock(scalingLazyState)
        }
    }
}
