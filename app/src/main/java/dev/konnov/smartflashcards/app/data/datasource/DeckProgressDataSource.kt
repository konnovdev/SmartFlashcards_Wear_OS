package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.data.model.CardProgressModel
import dev.konnov.smartflashcards.app.data.model.DeckProgressModel
import javax.inject.Inject

class DeckProgressDataSource @Inject constructor() {

    suspend fun get(deckId: String): DeckProgressModel = mockData

    suspend fun cardAnswered(deckId: String, cardId: Int, correct: Boolean) {
        setCardAnsweredMock(cardId, correct, deckId)
    }

    private fun setCardAnsweredMock(
        cardId: Int,
        correct: Boolean,
        deckId: String
    ) {
        val card = mockData.progress.find { it.cardId == cardId }

        if (card == null) {
            val cardProgress = if (correct) {
                CardProgressModel(deckId, cardId, 3, 1)
            } else {
                CardProgressModel(deckId, cardId, 1, 1)
            }
            (mockData.progress as MutableList).add(cardProgress)
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
                CardProgressModel(deckId, cardId, newRetention, card.times_card_shown + 1)

            (mockData.progress as MutableList).add(cardProgress)
        }
    }

    private val mockData = DeckProgressModel(
        mutableListOf(
            CardProgressModel("deck1", 2, 2, 4),
            CardProgressModel("deck1", 3, 5, 1),
        )
    )
}
