package dev.konnov.smartflashcards.app.data.repository

import dev.konnov.smartflashcards.app.data.converter.CardModelConverter
import dev.konnov.smartflashcards.app.data.datasource.CardLocalDataSource
import dev.konnov.smartflashcards.app.data.datasource.CardRemoteDataSource
import dev.konnov.smartflashcards.app.data.model.CardModel
import dev.konnov.smartflashcards.app.data.utils.RuntimeKeyValueCache
import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remoteDataSource: CardRemoteDataSource,
    private val localDataSource: CardLocalDataSource,
    private val converter: CardModelConverter,
    externalScope: CoroutineScope
) : CardRepository {

    private val cardsCache = RuntimeKeyValueCache<CacheKey, List<Card>>(externalScope) { params ->
        getCards(params.deckId)
    }

    override suspend fun get(deckId: String, cardId: CardId): Card =
        cardsCache
            .getOrUpdate(CacheKey(deckId))
            .first { it.id == cardId }

    override suspend fun getAll(deckId: String): List<Card> =
        cardsCache
            .getOrUpdate(CacheKey(deckId))

    // for now there's only one deck, so we don't need to pass deckIdx
    private suspend fun getCards(deckId: String): List<Card> =
        localDataSource.getAll(deckId)
            .ifEmpty {
                downloadDatabaseDump()
            }
            .map(converter::convert)

    private suspend fun downloadDatabaseDump(): List<CardModel> =
        remoteDataSource
            .getAll("deckId")
            .let { cards ->
                localDataSource.set(cards)
                cards
            }

    private data class CacheKey(
        val deckId: String
    )
}
