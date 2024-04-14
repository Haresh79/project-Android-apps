package com.example.memeapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("get_memes")
    fun getMeme(): Call<memedata>
}