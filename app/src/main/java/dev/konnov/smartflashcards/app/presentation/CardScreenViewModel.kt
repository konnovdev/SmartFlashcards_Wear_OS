package dev.konnov.smartflashcards.app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.usecase.GetNextCardUseCase
import dev.konnov.smartflashcards.app.domain.usecase.GetNumOfCardsToShowOnLaunchUseCase
import dev.konnov.smartflashcards.app.domain.usecase.SetCardAnsweredUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModel @Inject constructor(
    private val getNumOfCardsToShowUseCase: GetNumOfCardsToShowOnLaunchUseCase,
    private val getNextCardUseCase: GetNextCardUseCase,
    private val setCardAnsweredUseCase: SetCardAnsweredUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CardScreenState>(CardScreenState.Loading)
    val state: StateFlow<CardScreenState> = _state

    private val numOfCardsToShow = getNumOfCardsToShowUseCase()
    private var cardsShown = 0

    private var currentCard: Card? = null

    init {
        loadNewCard()
    }

    fun handleAction(action: CardScreenAction) {
        when (action) {
            CardScreenAction.RevealAnswerClicked -> {
                flipCard()
            }

            CardScreenAction.CardCorrect -> {
                if (cardsShown >= numOfCardsToShow) {
                    _state.value = CardScreenState.Finish
                } else {
                    setCurrentCardAnswered(answeredCorrectly = true) {
                        loadNewCard()
                    }
                }
            }

            CardScreenAction.CardWrong -> {
                if (cardsShown >= numOfCardsToShow) {
                    _state.value = CardScreenState.Finish
                } else {
                    setCurrentCardAnswered(answeredCorrectly = false) {
                        loadNewCard()
                    }
                }
            }
        }
    }

    private fun flipCard() {
        currentCard?.let {
            _state.value = CardScreenState.BackCard(it.back)
        } ?: Log.e("CardScreenViewModel", "Stored card object is null")
    }

    private fun loadNewCard() {
        viewModelScope.launch {
            runCatching { withContext(IO) { getNextCardUseCase() } }
                .onSuccess {
                    _state.value = CardScreenState.FrontCard(it.front)
                    currentCard = it
                    cardsShown++
                }
                .onFailure {
                    Log.e("CardScreenViewModel", "Error while loading new card", it)
                }
        }
    }

    private fun setCurrentCardAnswered(answeredCorrectly: Boolean, onAnswerRecorded: () -> Unit) {
        viewModelScope.launch {
            runCatching { withContext(IO) { setCardAnsweredUseCase(answeredCorrectly) } }
                .onSuccess { onAnswerRecorded() }
                .onFailure { Log.e("CardScreenViewModel", "Error while setting card answered", it) }
        }
    }
}
