package dev.konnov.smartflashcards.app.data.datasource

import androidx.datastore.core.DataStore
import dev.konnov.smartflashcards.app.UserPreferences
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserPreferencesDataSource @Inject constructor(
    private val datastore: DataStore<UserPreferences>
) {

    suspend fun getSelectedDeckId(): String? =
        datastore.data.first().deckId

    suspend fun setSelectedDeckId(deckId: String) {
        datastore.updateData { userPreferences ->
            userPreferences.toBuilder()
                .setDeckId(deckId)
                .build()
        }
    }

    suspend fun getCurrentCardId(): Int =
        datastore.data.first().cardId

    suspend fun setCurrentCardId(cardId: Int) {
        datastore.updateData { userPreferences ->
            userPreferences.toBuilder()
                .setCardId(cardId)
                .build()
        }
    }
}
