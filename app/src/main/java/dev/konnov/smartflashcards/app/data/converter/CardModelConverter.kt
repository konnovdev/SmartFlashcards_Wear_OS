package dev.konnov.smartflashcards.app.data.converter

import dev.konnov.smartflashcards.app.data.model.CardModel
import dev.konnov.smartflashcards.app.domain.entity.Card
import javax.inject.Inject

class CardModelConverter @Inject constructor() {

    fun convert(model: CardModel) = Card(model.front, model.back)
}
