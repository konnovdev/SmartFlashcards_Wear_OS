package dev.konnov.smartflashcards.app.data.converter

import dev.konnov.smartflashcards.app.data.model.CardModel
import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.CardId
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class CardModelConverter @Inject constructor() {

    fun convert(model: CardModel): Card = Card(CardId(model.card_id), model.front, model.back)

    fun convert(deckId: String, card: Card): CardModel = CardModel(deckId, card.id.value, card.front, card.back)

    fun convert(deckId: String, content: ByteArray): List<CardModel> {
        val cards = mutableListOf<CardModel>()

        val contentStr = String(content)
        val lines = contentStr.split("\n")
        lines
            .forEachIndexed { index, s ->
            val cardContentSplit = s.split(",")
            val cardFront = cardContentSplit[0]
            val cardBack = cardContentSplit.drop(1).joinToString("")
            cards.add(CardModel(deckId, index, cardFront, cardBack))
        }

        return cards
    }
}
