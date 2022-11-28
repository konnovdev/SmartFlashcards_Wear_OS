package dev.konnov.smartflashcards.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.konnov.smartflashcards.app.data.repository.CardRepositoryImpl
import dev.konnov.smartflashcards.app.data.repository.DeckProgressRepositoryImpl
import dev.konnov.smartflashcards.app.data.repository.SmartCardSelectorRepositoryImpl
import dev.konnov.smartflashcards.app.data.repository.UserPreferencesRepositoryImpl
import dev.konnov.smartflashcards.app.domain.repository.CardRepository
import dev.konnov.smartflashcards.app.domain.repository.DeckProgressRepository
import dev.konnov.smartflashcards.app.domain.repository.SmartCardSelectorRepository
import dev.konnov.smartflashcards.app.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindCardRepository(repository: CardRepositoryImpl): CardRepository

    @Binds
    @Singleton
    fun bindDeckProgressRepository(repository: DeckProgressRepositoryImpl): DeckProgressRepository

    @Binds
    @Singleton
    fun bindSmartCardSelectorRepository(repository: SmartCardSelectorRepositoryImpl): SmartCardSelectorRepository

    @Binds
    @Singleton
    fun bindUserPreferencesRepository(repository: UserPreferencesRepositoryImpl): UserPreferencesRepository

    companion object {

        @Provides
        fun coroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}
