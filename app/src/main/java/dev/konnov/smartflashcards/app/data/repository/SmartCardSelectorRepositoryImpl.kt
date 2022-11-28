package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.datasource.SmartCardSelectorDataSource
import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.repository.SmartCardSelectorRepository
import javax.inject.Inject

class SmartCardSelectorRepositoryImpl @Inject constructor(
    private val dataSource: SmartCardSelectorDataSource,
) : SmartCardSelectorRepository {

    override suspend fun selectNextCard(deckProgress: DeckProgress, cards: List<Card>): CardId =
        dataSource.selectNextCard(deckProgress, cards).let(::CardId)
}
