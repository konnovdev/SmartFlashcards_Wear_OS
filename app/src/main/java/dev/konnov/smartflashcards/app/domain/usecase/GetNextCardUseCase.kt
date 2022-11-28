package dev.konnov.smartflashcards.app.domain.usecase

import dev.konnov.smartflashcards.app.domain.entity.Card
import dev.konnov.smartflashcards.app.domain.repository.CardRepository
import dev.konnov.smartflashcards.app.domain.repository.DeckProgressRepository
import dev.konnov.smartflashcards.app.domain.repository.SmartCardSelectorRepository
import dev.konnov.smartflashcards.app.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class GetNextCardUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val deckProgressRepository: DeckProgressRepository,
    private val smartCardSelectorRepository: SmartCardSelectorRepository,
    private val cardRepository: CardRepository,
) {

    suspend operator fun invoke(): Card {
        val currentDeck = userPreferencesRepository.getSelectedDeckId()
        val deckProgress = deckProgressRepository.get(currentDeck)
        val allCards = cardRepository.getAll(currentDeck)
        val nextCardId = smartCardSelectorRepository.selectNextCard(deckProgress, allCards)
        userPreferencesRepository.setCurrentCardId(nextCardId)
        return cardRepository.get(currentDeck, nextCardId)
    }
}
