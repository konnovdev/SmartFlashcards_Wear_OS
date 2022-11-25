package dev.konnov.smartflashcards.app.data.datasource

import androidx.datastore.core.DataStore
import dev.konnov.smartflashcards.app.UserPreferences
import javax.inject.Inject
import kotlinx.coroutines.flow.first

class UserPreferencesDataSource @Inject constructor(
    private val datastore: DataStore<UserPreferences>
) {

    suspend fun getSelectedDeckId(): String =
        datastore.data.first().deckId ?: "deck1".also { setSelectedDeckId(it) }

    suspend fun setSelectedDeckId(deckId: String) {
        datastore.updateData { userPreferences ->
            userPreferences.toBuilder()
                .setDeckId(deckId)
                .build()
        }
    }

    suspend fun getCurrentCardId(): Int {
        val savedCardId = datastore.data.first().cardId
        if (savedCardId == 0) {
            return 1.also { setCurrentCardId(it) }
        }
        return savedCardId
    }

    suspend fun setCurrentCardId(cardId: Int) {
        datastore.updateData { userPreferences ->
            userPreferences.toBuilder()
                .setCardId(cardId)
                .build()
        }
    }
}
