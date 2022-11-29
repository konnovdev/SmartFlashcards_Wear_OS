package dev.konnov.smartflashcards.app.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.konnov.smartflashcards.app.data.model.CardModel
import dev.konnov.smartflashcards.app.data.model.CardProgressModel

@Database(entities = [CardModel::class, CardProgressModel::class], version = 1)
abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    abstract fun deckProgressDao(): DeckProgressDao
}
