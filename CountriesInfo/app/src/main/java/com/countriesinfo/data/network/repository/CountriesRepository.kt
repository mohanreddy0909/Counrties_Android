package com.countriesinfo.data.network.repository

import com.countriesinfo.data.model.CountiesInfoResponseItem
import com.countriesinfo.data.network.CountryApiService
import com.countriesinfo.data.network.NetworkStatus
import javax.inject.Inject

 class CountriesRepository @Inject constructor(private val countryApiService: CountryApiService) {
    suspend fun getPost(): NetworkStatus<List<CountiesInfoResponseItem>> {
        return try {
            val response = countryApiService.getPosts()
            NetworkStatus.Success(response)
        } catch (e: Exception) {
            NetworkStatus.Error("Failed to fetch countries", e)
        }
    }
}