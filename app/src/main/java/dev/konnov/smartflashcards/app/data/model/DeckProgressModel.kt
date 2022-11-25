package dev.konnov.smartflashcards.app.data.model

class DeckProgressModel(
    val progress: List<CardProgressModel>
)

data class CardProgressModel(
    val deckId: String,
    val cardId: Int,
    val retention: Int,
    val times_card_shown: Int
)
