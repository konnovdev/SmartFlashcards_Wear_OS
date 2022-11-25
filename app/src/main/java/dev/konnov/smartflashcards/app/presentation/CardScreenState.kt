package dev.konnov.smartflashcards.app.presentation

sealed interface CardScreenState {

    object Loading : CardScreenState

    data class FrontCard(val frontText: String) : CardScreenState

    data class BackCard(val backText: String) : CardScreenState

    object Finish : CardScreenState
}
