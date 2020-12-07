package com.android.data

import com.android.domain.Result
import java.io.IOException

suspend fun <T: Any> safeApiCall(
    call: suspend () -> Result<T>,
    errorMessage: String
) : Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Exception(Exception(errorMessage, e))
    }
}