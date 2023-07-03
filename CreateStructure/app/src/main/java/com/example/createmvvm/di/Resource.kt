package com.example.createmvvm.di


data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: Any?,
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Any): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }

        fun <T> exception(error: Throwable): Resource<T> {
            return Resource(Status.EXCEPTION, null, error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)

        }
    }
}