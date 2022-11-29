package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import javax.inject.Inject

class SmartCardSelectorDataSource @Inject constructor() {

    suspend fun selectNextCard(deckProgress: DeckProgress, cards: List<Card>): Int = selectMock(deckProgress, cards)

    private fun selectMock(deckProgress: DeckProgress, cards: List<Card>): Int =
        cards.map { it.id.value }
            .first { cardId -> cardId !in deckProgress.progress.map { it.cardId.value } }
}
