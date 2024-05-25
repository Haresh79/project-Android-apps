package com.example.weather

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
val apiKey="04c7796059ff63122ed124d813af4a45"
        val retrofitClient= Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiService::class.java)
        getWeather(retrofitClient,"Balasore", apiKey)
        findViewById<ImageView>(R.id.search_btn).setOnClickListener {
            var input= findViewById<EditText>(R.id.editTextText).text
            if (input.isNotEmpty()){
                getWeather(retrofitClient, input.toString(), apiKey)
                findViewById<EditText>(R.id.editTextText).text.clear()
            }
            else{
                Toast.makeText(this,"Please Enter Your Location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getWeather(retrofitClient: apiService, s: String, apiKey: String) {
        val call = retrofitClient.getWeather(s.toString(),apiKey)
        call.enqueue(object : Callback<dataclass>{
            override fun onResponse(call: Call<dataclass>, response: Response<dataclass>) {
                val responseBody=response.body()
                val main= responseBody?.main

                findViewById<TextView>(R.id.Loaction).text= responseBody?.name.toString()
                findViewById<TextView>(R.id.Temp).text= KtoD(main?.temp).toString()+"°C"
                findViewById<TextView>(R.id.feelslike).text= "Fells Like : "+KtoD(main?.feels_like).toString()+"°C"
                findViewById<TextView>(R.id.hum).text= main?.humidity.toString()+"%"
                findViewById<TextView>(R.id.pre).text= main?.pressure.toString()+" mb"
                findViewById<TextView>(R.id.ws).text= responseBody?.wind?.speed.toString()+" km/h"
                setBG(responseBody?.weather?.get(0)?.icon.toString())
            }

            override fun onFailure(call: Call<dataclass>, t: Throwable) {
                Log.e("WeatherApp", "Failed to make API call: ${t.message}", t)
                Toast.makeText(this@MainActivity, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun KtoD(kelvin: Double?): Int? {
        val degcel = (kelvin?.minus(273))?.toInt()
        return degcel
    }
    private fun setBG(s:String){
        var resourceId=R.drawable.ic_launcher_background
        if (s=="01d" || s=="01n"){
            resourceId=R.drawable.pic2
        }
        else if (s=="02d" || s=="02n" || s=="03n" || s=="03d" || s=="04n" || s=="04d"){
            resourceId=R.drawable.pic1
        }
        else if(s=="09d" || s=="09n" || s=="10d" || s=="10n"){
            resourceId= R.drawable.pic3
        }
        else if (s=="11d" || s=="11n"){
            resourceId=R.drawable.pic4
        }
        else if (s=="13d" || s=="13n"){
            resourceId=R.drawable.pic5
        }
        else if (s=="50n"||s=="50d"){
            resourceId=R.drawable.pic6
        }
        else{
            resourceId=R.drawable.pic1
        }
        findViewById<ImageView>(R.id.iv).setImageResource(resourceId)
    }

}