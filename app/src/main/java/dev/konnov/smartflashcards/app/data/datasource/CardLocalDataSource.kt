package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.data.database.room.CardDao
import dev.konnov.smartflashcards.app.data.model.CardModel
import javax.inject.Inject

class CardLocalDataSource @Inject constructor(
    private val cardDao: CardDao
) {

    suspend fun getAll(deckId: String): List<CardModel> = cardDao.getAll()

    suspend fun set(cards: List<CardModel>) {
        cardDao.insertDeck(cards)
    }
}
