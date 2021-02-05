package pl.senordeveloper.code16challenge

sealed class Result<T> {
    abstract fun <R> mapResult(function: (T) -> R): Result<R>

    data class Loading<T>(val cache: T? = null) : Result<T>() {
        override fun <R> mapResult(function: (T) -> R): Result<R> =
            Loading(cache?.let { function(it) })
    }

    data class Error<T>(val throwable: Throwable) : Result<T>() {
        override fun <R> mapResult(function: (T) -> R) = Error<R>(throwable)
    }

    data class Success<T>(val result: T) : Result<T>() {
        override fun <R> mapResult(function: (T) -> R): Result<R> = Result.Success(result = function(result))
    }
}
