package dev.konnov.smartflashcards.app.domain.repository

import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress

interface DeckProgressRepository {

    suspend fun get(deckId: String): DeckProgress

    suspend fun cardAnswered(cardId: CardId, deckId: String, correct: Boolean)
}
