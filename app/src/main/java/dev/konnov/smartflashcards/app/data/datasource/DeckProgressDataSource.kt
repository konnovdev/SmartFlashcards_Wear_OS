package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.data.database.room.DeckProgressDao
import dev.konnov.smartflashcards.app.data.model.CardProgressModel
import javax.inject.Inject

class DeckProgressDataSource @Inject constructor(
    private val deckProgressDao: DeckProgressDao
) {

    suspend fun get(deckId: String): List<CardProgressModel> =
        deckProgressDao.getAll() // we don't care about deck id for now

    suspend fun cardAnswered(deckId: String, cardId: Int, correct: Boolean) {
        setCardAnswered(cardId, correct, deckId)
    }

    // TODO make a more complex algorithm
    private suspend fun setCardAnswered(
        cardId: Int,
        correct: Boolean,
        deckId: String
    ) {
        val card =
            deckProgressDao.getAll().find { it.cardId == cardId } // use SELECT WHERE statement

        val currentTimestamp = System.currentTimeMillis()

        if (card == null) {
            val cardProgress = if (correct) {
                CardProgressModel(deckId, cardId, 3, 1, currentTimestamp)
            } else {
                CardProgressModel(deckId, cardId, 1, 1, currentTimestamp)
            }
            deckProgressDao.insertDeck(cardProgress)
        } else {
            var newRetention: Int
            if (correct) {
                newRetention = card.retention + 1
            } else {
                newRetention = card.retention - 1
            }

            if (newRetention < 1) {
                newRetention = 1
            } else if (newRetention > 5) {
                newRetention = 5
            }
            val cardProgress =
                CardProgressModel(
                    deckId,
                    cardId,
                    newRetention,
                    card.timesCardShown + 1,
                    currentTimestamp
                )

            deckProgressDao.insertDeck(cardProgress)
        }
    }
}
