package dev.konnov.smartflashcards.app.domain.repository

import dev.konnov.smartflashcards.app.domain.entity.CardId

interface UserPreferencesRepository {

    suspend fun getSelectedDeckId(): String

    suspend fun setSelectedDeckId(deckId: String)

    suspend fun getCurrentCardId(): CardId

    suspend fun setCurrentCardId(cardId: CardId)
}
