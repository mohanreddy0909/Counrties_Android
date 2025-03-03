package com.countriesinfo.data.network

import com.countriesinfo.data.model.CountriesInfoResponse
import retrofit2.http.GET

interface CountryApiService {
    @GET("db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getPosts(): CountriesInfoResponse
}