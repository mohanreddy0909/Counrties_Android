package com.countriesinfo.module


import com.countriesinfo.data.network.CountryApiService
import com.countriesinfo.data.network.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/"
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)  // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):CountryApiService{
        return retrofit.create(CountryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(countryApiService: CountryApiService):CountriesRepository{
        return CountriesRepository(countryApiService)
    }

}