package dev.konnov.smartflashcards.app.domain.entity

data class DeckProgress(
    val deckId: String,
    val progress: List<CardProgress>
)

data class CardProgress(val cardId: CardId, val retention: Retention, val timesCardShown: Int)

enum class Retention(val retentionLevel: Int) {
    COMPLETELY_FORGOT(1),
    PARTIALLY_FORGOT(2),
    SOMEWHAT_REMEMBER(3),
    REMEMBER(4),
    KNOW_BY_HEART(5);

    companion object {
        fun fromInt(value: Int) = values().first { it.retentionLevel == value }
    }
}
