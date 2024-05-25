package com.example.weather

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface apiService {
    @GET("weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): retrofit2.Call<dataclass>
}