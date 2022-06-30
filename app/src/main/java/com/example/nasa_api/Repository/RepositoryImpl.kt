package com.example.nasa_api.Repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository {
    val baseUrl = "https://api.nasa.gov/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    fun getPictureOfTheDayAPI(): PictureOfTheDayAPI {
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }

    override fun getPictureOfTheDay() {
        TODO("Not yet implemented")
    }
}