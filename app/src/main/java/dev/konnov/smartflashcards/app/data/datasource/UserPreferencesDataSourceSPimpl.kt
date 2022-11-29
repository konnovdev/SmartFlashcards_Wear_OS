package dev.konnov.smartflashcards.app.data.datasource

import android.content.SharedPreferences
import javax.inject.Inject

// This is an implementation of the UserPreferencesDataSource using Shared Preferences
// Because Proto Datastore is hard to debug and has a lot of issues
class UserPreferencesDataSourceSPimpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getSelectedDeckId(): String? =
        sharedPreferences.getString(KEY_DECK_ID, null)

    fun setSelectedDeckId(deckId: String) {
       sharedPreferences.edit().putString(KEY_DECK_ID, deckId).apply()
    }

    fun getCurrentCardId(): Int =
        sharedPreferences.getInt(KEY_CARD_ID, 0)

    fun setCurrentCardId(cardId: Int) {
        sharedPreferences.edit().putInt(KEY_CARD_ID, cardId).apply()
    }

    private companion object {
        const val KEY_DECK_ID = "deckId"
        const val KEY_CARD_ID = "cardId"
    }
}