package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import javax.inject.Inject

class SmartCardSelectorDataSource @Inject constructor() {

    suspend fun selectNextCard(deckProgress: DeckProgress): Int = selectMock(deckProgress)

    private fun selectMock(deckProgress: DeckProgress): Int =
        listOf(1, 2, 3, 4, 5).first { cardId -> deckProgress.progress.none { it.cardId.value == cardId } }
}
