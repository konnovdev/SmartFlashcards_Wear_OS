package dev.konnov.smartflashcards.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
import dev.konnov.smartflashcards.app.UserPreferences
import androidx.room.Room
import dev.konnov.smartflashcards.app.data.database.room.CardDao
import dev.konnov.smartflashcards.app.data.database.room.DeckProgressDao
import dev.konnov.smartflashcards.app.data.database.room.RoomAppDatabase
import dev.konnov.smartflashcards.app.data.database.userPreferencesDataStore

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

        @Singleton
        @Provides
        fun coroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        @Singleton
        @Provides
        fun provideUserPreferencesDataStore(
            @ApplicationContext context: Context
        ): DataStore<UserPreferences> = context.userPreferencesDataStore

        @Singleton
        @Provides
        fun provideAppDb(
            @ApplicationContext appContext: Context
        ): RoomAppDatabase = Room.databaseBuilder(
            appContext,
            RoomAppDatabase::class.java, "room-db"
        ).build()

        @Singleton
        @Provides
        fun provideCardDao(
            db: RoomAppDatabase
        ): CardDao = db.cardDao()

        @Singleton
        @Provides
        fun provideDeckProgressDao(
            db: RoomAppDatabase
        ): DeckProgressDao = db.deckProgressDao()

        @Singleton
        @Provides
        fun provideUserSharedPreferences(
            @ApplicationContext appContext: Context
        ): SharedPreferences = appContext.getSharedPreferences(
            "user_shared_preferences",
            Context.MODE_PRIVATE
        )
    }
}
