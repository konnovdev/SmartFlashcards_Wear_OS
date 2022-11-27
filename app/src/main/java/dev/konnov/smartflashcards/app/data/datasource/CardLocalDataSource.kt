package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.data.model.CardModel
import javax.inject.Inject

// TODO implement a database here
class CardLocalDataSource @Inject constructor() {

    private val cardsFakeDatabase = mutableListOf<CardModel>()

    suspend fun getAll(deckId: String): List<CardModel> = cardsFakeDatabase

    suspend fun set(cards: List<CardModel>) {
        cardsFakeDatabase.addAll(cards)
    }
}
