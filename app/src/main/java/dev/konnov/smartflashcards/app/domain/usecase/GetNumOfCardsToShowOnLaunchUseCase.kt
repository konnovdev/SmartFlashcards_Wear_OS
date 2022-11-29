package dev.konnov.smartflashcards.app.domain.usecase

import javax.inject.Inject

class GetNumOfCardsToShowOnLaunchUseCase @Inject constructor() {

    operator fun invoke(): Int = 2
}
