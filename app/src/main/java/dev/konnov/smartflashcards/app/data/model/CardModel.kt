package dev.konnov.smartflashcards.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardModel(
    @ColumnInfo(name = "deckId") val deckId: String,
    @ColumnInfo(name = "cardId") @PrimaryKey val card_id: Int,
    @ColumnInfo(name = "front") val front: String,
    @ColumnInfo(name = "back") val back: String
)
