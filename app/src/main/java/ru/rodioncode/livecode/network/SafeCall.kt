package ru.rodioncode.livecode.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> T
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            NetworkResult.Success(block())
        } catch (e: IOException) {
            NetworkResult.Error(e, "No internet connection")
        } catch (e: HttpException) {
            NetworkResult.Error(e, "Server error: ${e.code()}")
        } catch (e: Exception) {
            NetworkResult.Error(e, e.message)
        }
    }
}