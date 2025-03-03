package com.countriesinfo.data.network

sealed class NetworkStatus<out T> {
    data class Success<out T>(val data: T) : NetworkStatus<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : NetworkStatus<Nothing>()
    object Loading : NetworkStatus<Nothing>()
}