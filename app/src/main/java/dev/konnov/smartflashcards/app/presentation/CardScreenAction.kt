package dev.konnov.smartflashcards.app.presentation

sealed interface CardScreenAction {

    object RevealAnswerClicked : CardScreenAction

    object CardWrong : CardScreenAction

    object CardCorrect : CardScreenAction
}
