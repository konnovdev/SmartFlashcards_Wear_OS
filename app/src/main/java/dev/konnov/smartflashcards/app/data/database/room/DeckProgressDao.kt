package dev.konnov.smartflashcards.app.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.konnov.smartflashcards.app.data.model.CardProgressModel

@Dao
interface DeckProgressDao  {

    @Query("SELECT * FROM deckProgress")
    suspend fun getAll(): List<CardProgressModel>

    @Insert
    suspend fun insertDeck(cardProgress: CardProgressModel)
}
