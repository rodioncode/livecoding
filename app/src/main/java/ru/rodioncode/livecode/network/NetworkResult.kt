package ru.rodioncode.livecode.network

sealed interface NetworkResult<out T> {

    data class Success<T>(val data: T) : NetworkResult<T>

    data class Error(
        val exception: Throwable? = null,
        val message: String? = null,
    ) : NetworkResult<Nothing>
}

fun <T, R> NetworkResult<T>.map(transform: (T) -> R) : NetworkResult<R> {
    return when (this) {
        is NetworkResult.Error -> this
        is NetworkResult.Success<T> -> NetworkResult.Success(transform(this.data))
    }
}

fun <T> NetworkResult<T>.isSuccess() = this is NetworkResult.Success
