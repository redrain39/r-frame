package com.redrain.sup_base.data.common

sealed class DataState<out R> {
    object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val message: String?) : DataState<Nothing>()
    object Error: DataState<Nothing>()
}