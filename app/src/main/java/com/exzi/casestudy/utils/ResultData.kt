package com.exzi.casestudy.utils

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Failure(val error: Throwable) : ResultData<Nothing>()
    data object Loading : ResultData<Nothing>()
}