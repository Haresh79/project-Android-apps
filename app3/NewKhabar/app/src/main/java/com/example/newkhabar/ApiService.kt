package com.example.newkhabar

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("everything?q=apple&from=2024-04-14&to=2024-04-14&sortBy=popularity&apiKey=3f35a504c43b47139715a79e8569b2fb")
    fun getNews():Call<newsData>
}
