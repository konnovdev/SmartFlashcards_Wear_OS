package dev.konnov.smartflashcards.app.data.converter

import dev.konnov.smartflashcards.app.data.model.CardProgressModel
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.CardProgress
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.entity.Retention
import javax.inject.Inject

class DeckProgressConverter @Inject constructor() {

    fun convert(cardProgressModel: List<CardProgressModel>): DeckProgress =
        DeckProgress(
            cardProgressModel.firstOrNull()?.deckId ?: "deck1",
            cardProgressModel.map {
                CardProgress(
                    CardId(it.cardId),
                    Retention.fromInt(it.retention),
                    it.timesCardShown,
                    it.lastShownTimestamp
                )
            }
        )
}
