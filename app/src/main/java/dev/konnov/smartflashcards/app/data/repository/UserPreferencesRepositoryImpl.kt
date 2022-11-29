package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.datasource.UserPreferencesDataSource
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    private var cachedDeckId: String? = null
    private var cachedCardId: CardId? = null

    override suspend fun getSelectedDeckId(): String {
        if (cachedDeckId == null) {
            val deckIdFromDataSource = dataSource.getSelectedDeckId()
            if (deckIdFromDataSource.isNullOrEmpty()) {
                val defaultDeck1 = "deck1"
                cachedDeckId = defaultDeck1
                dataSource.setSelectedDeckId("deck1")
            }
        }
        return requireNotNull(cachedDeckId)
    }

    override suspend fun setSelectedDeckId(deckId: String) {
        cachedDeckId = deckId
        dataSource.setSelectedDeckId(deckId)
    }

    override suspend fun getCurrentCardId(): CardId {
        if (cachedCardId == null) {
            val savedCardId = dataSource.getCurrentCardId()
            cachedCardId = CardId(savedCardId)
        }
        return requireNotNull(cachedCardId)
    }

    override suspend fun setCurrentCardId(cardId: CardId) {
        cachedCardId = cardId
        dataSource.setCurrentCardId(cardId.value)
    }
}
