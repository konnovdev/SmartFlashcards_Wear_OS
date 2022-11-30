package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.entity.Retention.KNOW_BY_HEART
import dev.konnov.smartflashcards.app.domain.entity.Retention.COMPLETELY_FORGOT
import dev.konnov.smartflashcards.app.domain.entity.Retention.PARTIALLY_FORGOT
import dev.konnov.smartflashcards.app.domain.entity.Retention.SOMEWHAT_REMEMBER
import java.util.*
import javax.inject.Inject

class SmartCardSelectorDataSource @Inject constructor() {

    fun selectNextCard(deckProgress: DeckProgress, cards: List<Card>): Int {
        if (needToShowMoreNewCards(deckProgress)) {
            val newCardsWithSameWordsAsLearntBefore = getNewCardsWithSameWordsAsLearntBefore(
                cards,
                deckProgress
            )

            return if (newCardsWithSameWordsAsLearntBefore.isNotEmpty()) {
                newCardsWithSameWordsAsLearntBefore.first().id.value
            } else {
                getNotStudiedCards(cards, deckProgress).first().id.value
            }
        }

        return getForgottenCards(deckProgress, cards).first()
    }

    private fun getNewCardsWithSameWordsAsLearntBefore(
        cards: List<Card>,
        deckProgress: DeckProgress,
    ): Set<Card> {
        val newCardsWithSameWordsAsLearntBefore = mutableSetOf<Card>()

        for (i in 0 until cards.size) {
            for (j in 0 until cards.size) {
                if (cards[j].front.lowercase(Locale.getDefault())
                        .contains(cards[i].front.lowercase(Locale.getDefault()))
                    && cards[i].id in deckProgress.progress.map { it.cardId }
                    && (deckProgress.progress.find { it.cardId == cards[i].id }?.retention
                        ?: COMPLETELY_FORGOT) in SOMEWHAT_REMEMBER..KNOW_BY_HEART
                    && cards[j].id !in deckProgress.progress.map { it.cardId }
                ) {
                    newCardsWithSameWordsAsLearntBefore.add(cards[j])
                }
            }
        }
        return newCardsWithSameWordsAsLearntBefore
    }

    private fun getNotStudiedCards(
        cards: List<Card>,
        deckProgress: DeckProgress,
    ): Set<Card> =
        cards.filter { it.id !in deckProgress.progress.map { it.cardId } }.toSet()

    private fun getForgottenCards(deckProgress: DeckProgress, cards: List<Card>): List<Int> =
        cards.mapNotNull { card -> deckProgress.progress.find { it.cardId == card.id } }
            .filter { it.retention in COMPLETELY_FORGOT..PARTIALLY_FORGOT }
            .sortedBy { it.lastShownTimestamp }
            .map { it.cardId.value }


    private fun needToShowMoreNewCards(deckProgress: DeckProgress): Boolean {
        val currentTimestamp = System.currentTimeMillis()
        val oneDay = 24 * 60 * 60 * 1000

        val forgottenCards = deckProgress
            .progress
            .filter { it.retention in COMPLETELY_FORGOT..PARTIALLY_FORGOT }
            .filter { currentTimestamp - it.timesCardShown > oneDay }
            .size

        val newCardsShownThisWeek = deckProgress
            .progress
            .filter { it.lastShownTimestamp in currentTimestamp - 7 * oneDay..currentTimestamp }
            .filter { it.timesCardShown == 1 }
            .size

        return forgottenCards < CAN_KEEP_FORGOTTEN_CARDS && newCardsShownThisWeek < SHOW_NEW_CARDS_PER_WEEK
    }

    private companion object {
        private const val SHOW_NEW_CARDS_PER_WEEK = 10
        private const val CAN_KEEP_FORGOTTEN_CARDS = 3
    }
}
