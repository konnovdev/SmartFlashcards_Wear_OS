package dev.konnov.smartflashcards.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deckProgress")
data class CardProgressModel(
    @ColumnInfo(name = "deckId") val deckId: String,
    @ColumnInfo(name = "cardId") @PrimaryKey val cardId: Int,
    @ColumnInfo(name = "retention") val retention: Int,
    @ColumnInfo(name = "timesCardShown") val times_card_shown: Int
)
