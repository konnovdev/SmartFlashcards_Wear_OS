package dev.konnov.smartflashcards.app.domain.usecase

import dev.konnov.smartflashcards.app.domain.repository.DeckProgressRepository
import dev.konnov.smartflashcards.app.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SetCardAnsweredUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val deckProgressRepository: DeckProgressRepository
) {

    suspend operator fun invoke(answeredCorrectly: Boolean) {
        val cardId = userPreferencesRepository.getCurrentCardId()
        val deckId = userPreferencesRepository.getSelectedDeckId()
        deckProgressRepository.cardAnswered(cardId, deckId, answeredCorrectly)
    }
}
