package dev.konnov.smartflashcards.app.domain.repository

import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress

interface SmartCardSelectorRepository {

    suspend fun selectNextCard(deckProgress: DeckProgress): CardId
}
