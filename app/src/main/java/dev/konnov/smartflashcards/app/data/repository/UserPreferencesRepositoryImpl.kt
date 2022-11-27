package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.datasource.UserPreferencesDataSource
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override suspend fun getSelectedDeckId(): String = dataSource.getSelectedDeckId()

    override suspend fun setSelectedDeckId(deckId: String) {
        dataSource.setSelectedDeckId(deckId)
    }

    override suspend fun getCurrentCardId(): CardId = dataSource.getCurrentCardId().let(::CardId)

    override suspend fun setCurrentCardId(cardId: CardId) {
        dataSource.setCurrentCardId(cardId.value)
    }
}
