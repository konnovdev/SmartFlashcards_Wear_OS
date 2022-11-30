package dev.konnov.smartflashcards.app

import dev.konnov.smartflashcards.app.data.datasource.SmartCardSelectorDataSource
import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.entity.CardId
import dev.konnov.smartflashcards.app.domain.entity.CardProgress
import dev.konnov.smartflashcards.app.domain.entity.DeckProgress
import dev.konnov.smartflashcards.app.domain.entity.Retention
import junit.framework.Assert.assertEquals
import org.junit.Test

class SmartCardSelectorDataSourceTest {

    private val dataSource = SmartCardSelectorDataSource()

    private val cards = listOf(
        Card(id = CardId(1), front = "Cat", back = "a feline animal"),
        Card(
            id = CardId(2),
            front = "Feat",
            back = "An achievement that requires great courage, skill, or strength"
        ),
        Card(
            id = CardId(3),
            front = "A Cat in Gloves Catches no Mice",
            back = "If you are too polite or careful, you might not achieve what you want."
        ),
        Card(
            id = CardId(4),
            front = "Appease",
            back = "Pacify or placate (someone) by acceding to their demands"
        ),
    )

    @Test
    fun `empty progress EXPECT return first card id`() {
        val deckProgress = DeckProgress(
            deckId = DECK_ID,
            progress = emptyList()
        )


        val result = dataSource.selectNextCard(deckProgress, cards)

        assertEquals(1, result)
    }

    @Test
    fun `3 cards answered correct recently EXPECT show last card`() {
        val currentTimestamp = System.currentTimeMillis()
        val deckProgress = DeckProgress(
            deckId = DECK_ID,
            progress = listOf(
                CardProgress(
                    cardId = CardId(1),
                    retention = Retention.KNOW_BY_HEART,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 1
                ),
                CardProgress(
                    cardId = CardId(2),
                    retention = Retention.KNOW_BY_HEART,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 1
                ),
                CardProgress(
                    cardId = CardId(3),
                    retention = Retention.KNOW_BY_HEART,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 1
                ),
            )
        )

        val result = dataSource.selectNextCard(deckProgress, cards)

        assertEquals(4, result)
    }

    @Test
    fun `cat word learned EXPECT show card that also contains this word`() {
        val currentTimestamp = System.currentTimeMillis()
        val deckProgress = DeckProgress(
            deckId = DECK_ID,
            progress = listOf(
                CardProgress(
                    cardId = CardId(1),
                    retention = Retention.SOMEWHAT_REMEMBER,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 1
                ),
            )
        )

        val result = dataSource.selectNextCard(deckProgress, cards)

        assertEquals(3, result)
    }

    @Test
    fun `all words forgotten EXPECT show first oldest forgotten word`() {
        val currentTimestamp = System.currentTimeMillis()
        val deckProgress = DeckProgress(
            deckId = DECK_ID,
            progress = listOf(
                CardProgress(
                    cardId = CardId(1),
                    retention = Retention.COMPLETELY_FORGOT,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 100
                ),
                CardProgress(
                    cardId = CardId(2),
                    retention = Retention.COMPLETELY_FORGOT,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 10
                ),
                CardProgress(
                    cardId = CardId(3),
                    retention = Retention.COMPLETELY_FORGOT,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 10000
                ),
                CardProgress(
                    cardId = CardId(4),
                    retention = Retention.COMPLETELY_FORGOT,
                    timesCardShown = 1,
                    lastShownTimestamp = currentTimestamp - 15
                ),
            )
        )

        val result = dataSource.selectNextCard(deckProgress, cards)

        assertEquals(3, result)
    }

    private companion object {
        private const val DECK_ID = "deckId"
    }
}