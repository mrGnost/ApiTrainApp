package com.example.apitrainapp.repository

import com.example.apitrainapp.data.api.DogsApi
import javax.inject.Inject

class DogsRepository @Inject constructor(private val api: DogsApi) {
    suspend fun getDog() = api.getDog()
}