package dev.konnov.smartflashcards.app.domain.repository

import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.CardId

interface CardRepository {

    suspend fun get(deckId: String, cardId: CardId): Card

    suspend fun getAll(deckId: String): List<Card>
}
