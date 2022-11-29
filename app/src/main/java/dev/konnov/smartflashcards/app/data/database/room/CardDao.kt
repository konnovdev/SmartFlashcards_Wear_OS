package dev.konnov.smartflashcards.app.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.konnov.smartflashcards.app.data.model.CardModel

@Dao
interface CardDao {

    @Query("SELECT * FROM card")
    suspend fun getAll(): List<CardModel>

    @Insert
    suspend fun insertDeck(cardsInDeck: List<CardModel>)
}
