package com.example.apitrainapp.data.api

import com.example.apitrainapp.data.model.DogSample
import retrofit2.Response
import retrofit2.http.GET

interface DogsApi {
    @GET("/api/breeds/image/random")
    suspend fun getDog(): Response<DogSample>
}