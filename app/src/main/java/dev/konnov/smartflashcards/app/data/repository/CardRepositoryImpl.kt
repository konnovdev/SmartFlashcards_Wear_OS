package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.converter.CardModelConverter
import dev.konnov.smartflashcards.app.data.datasource.CardDataSource
import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val dataSource: CardDataSource,
    private val converter: CardModelConverter
) : CardRepository {

    override suspend fun get(deckId: String, cardId: CardId): Card =
        dataSource.get(deckId, cardId.value).let(converter::convert)
}
