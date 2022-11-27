package dev.konnov.smartflashcards.app.data.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RuntimeKeyValueCache<K, V>(
    private val coroutineScope: CoroutineScope,
    private val validator: suspend (V) -> Boolean = { true },
    private val producer: suspend (K) -> V,
) {

    private val _cache = MutableStateFlow<Map<K, V>>(mapOf())
    val cache: StateFlow<Map<K, V>> = _cache.asStateFlow()

    private val mutex = Mutex()
    private val asyncValues: MutableMap<K, Deferred<Result<V>>> = mutableMapOf()

    suspend fun getOrUpdate(key: K, force: Boolean = false): V =
        cache.value[key]?.takeUnless { force } ?: mutex.withLock {
            cache.value[key]?.takeUnless { force }
                ?.let { return it }
                ?: updateAsync(key)
        }.await().getOrThrow()

    private suspend fun updateAsync(key: K): Deferred<Result<V>> =
        asyncValues[key] ?: coroutineScope.async(Dispatchers.IO) {
            runCatching { producer(key) }.also { result ->
                val cachedValue = result.getOrNull()?.takeIf { validator(it) }
                mutex.withLock {
                    cachedValue?.let { value ->
                        _cache.value = _cache.value.plus(key to value)
                    }
                    asyncValues.remove(key)
                }
            }
        }.also { asyncValues[key] = it }
}
