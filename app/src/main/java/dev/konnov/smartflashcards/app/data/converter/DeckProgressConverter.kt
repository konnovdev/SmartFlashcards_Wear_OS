package dev.konnov.smartflashcards.app.data.converter

import dev.konnov.smartflashcards.app.data.model.DeckProgressModel
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.CardProgress
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.entity.Retention
import javax.inject.Inject

class DeckProgressConverter @Inject constructor() {

    fun convert(deckProgressModel: DeckProgressModel): DeckProgress =
        DeckProgress(
            deckProgressModel.progress.first().deckId,
            deckProgressModel.progress.map {
                CardProgress(
                    CardId(it.cardId),
                    Retention.fromInt(it.retention),
                    it.times_card_shown
                )
            }
        )
}
