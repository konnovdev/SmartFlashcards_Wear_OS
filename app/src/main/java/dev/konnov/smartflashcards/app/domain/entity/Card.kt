package dev.konnov.smartflashcards.app.domain.entity

data class Card(
    val id: CardId,
    val front: String,
    val back: String,
)
