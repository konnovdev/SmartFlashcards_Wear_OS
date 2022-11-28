package dev.konnov.smartflashcards.app.data.datasource

import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(){

    private var selectedMockDeckId = "deck1"

    private var selectedMockCardId = 1

    suspend fun getSelectedDeckId(): String = selectedMockDeckId

    suspend fun setSelectedDeckId(deckId: String) {
        selectedMockDeckId = deckId
    }

    suspend fun getCurrentCardId(): Int = selectedMockCardId

    suspend fun setCurrentCardId(cardId: Int) {
        selectedMockCardId = cardId
    }
}
