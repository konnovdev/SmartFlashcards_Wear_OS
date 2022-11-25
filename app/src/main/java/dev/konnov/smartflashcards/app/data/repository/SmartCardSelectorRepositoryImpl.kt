package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.datasource.SmartCardSelectorDataSource
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.repository.SmartCardSelectorRepository
import javax.inject.Inject

class SmartCardSelectorRepositoryImpl @Inject constructor(
    private val dataSource: SmartCardSelectorDataSource,
) : SmartCardSelectorRepository {

    override suspend fun selectNextCard(deckProgress: DeckProgress): CardId =
        dataSource.selectNextCard(deckProgress).let(::CardId)
}
