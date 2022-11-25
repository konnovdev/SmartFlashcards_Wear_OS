package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.converter.DeckProgressConverter
import dev.konnov.smartflashcards.app.data.datasource.DeckProgressDataSource
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.repository.DeckProgressRepository
import javax.inject.Inject

class DeckProgressRepositoryImpl @Inject constructor(
    private val dataSource: DeckProgressDataSource,
    private val converter: DeckProgressConverter
) : DeckProgressRepository {

    override suspend fun get(deckId: String): DeckProgress =
        dataSource
            .get(deckId)
            .let { converter.convert(it) }

    override suspend fun cardAnswered(cardId: CardId, deckId: String, correct: Boolean) {
        dataSource.cardAnswered(deckId, cardId.value, correct)
    }
}
