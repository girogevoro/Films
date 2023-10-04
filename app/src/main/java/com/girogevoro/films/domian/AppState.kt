package com.girogevoro.films.domian

sealed interface AppState<out T> {
    data class Success<out T>(val data: T) : AppState<T>
    data class Error(val error: Throwable) : AppState<Nothing>
    object Loading : AppState<Nothing>
}

