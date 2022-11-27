package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.data.converter.CardModelConverter
import dev.konnov.smartflashcards.app.data.model.CardModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import javax.inject.Inject

class CardRemoteDataSource @Inject constructor(
    private val converter: CardModelConverter
) {

    private val client = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }

    // Deck id is not needed for MVP
    suspend fun getAll(deckId: String): List<CardModel> {
        val url = "https://chineseguide.net/sandbox/files/ankitest.csv" // temporary URL

        val response = client.get(url)

        return converter.convert(deckId, response.readBytes())
    }
}
