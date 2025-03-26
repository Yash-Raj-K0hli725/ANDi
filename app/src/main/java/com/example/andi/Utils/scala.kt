package com.example.andi.Utils

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface scala {
    @GET
    suspend fun getRspns(@Url url: String): Response<String>
}

class RetroInstance(){
    private val base_url = "https://www.andisoftwaresolutions.com/"
    fun getInstance():scala{
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(base_url)
            .build()
            .create(scala::class.java)
    }
}