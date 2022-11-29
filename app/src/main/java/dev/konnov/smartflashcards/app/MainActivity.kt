package dev.konnov.smartflashcards.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import dev.konnov.smartflashcards.app.presentation.CardScreenViewModel
import dev.konnov.smartflashcards.app.theme.SmartFlashcardsTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.konnov.smartflashcards.app.presentation.CardScreenAction
import dev.konnov.smartflashcards.app.presentation.CardScreenState
import dev.konnov.smartflashcards.app.ui.CardBackScreen
import dev.konnov.smartflashcards.app.ui.CardFrontScreen

@AndroidEntryPoint
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
            ShowCard(lazyListState = scalingLazyState, viewModel())
        }
    }
}

@Composable
fun ShowCard(
    lazyListState: ScalingLazyListState,
    viewModel: CardScreenViewModel
) {
    val state by viewModel.state.collectAsState()

    when (val screenState = state) {
        is CardScreenState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is CardScreenState.FrontCard -> {
            CardFrontScreen(
                screenState.frontText
            ) { viewModel.handleAction(CardScreenAction.RevealAnswerClicked) }
        }

        is CardScreenState.BackCard -> {
            CardBackScreen(
                lazyListState = lazyListState,
                definition = screenState.backText,
                correctClicked = {
                    viewModel.handleAction(CardScreenAction.CardCorrect)
                },
                incorrectClicked = {
                    viewModel.handleAction(CardScreenAction.CardWrong)
                })
        }

        is CardScreenState.Finish -> {
            Text( // TODO replace with some nice screen
                text = "FINISHED!!!",
                Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

