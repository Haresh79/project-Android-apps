package com.example.newkhabar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var Adapter: MyAdapter
    lateinit var RecyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        RecyclerView= findViewById(R.id.myRv)
        apiClient()


    }
    private  fun apiClient() {

        val RetrofitClient= Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val call = RetrofitClient.getNews()
        call.enqueue(object : Callback<newsData>{
//            Toast.makeText(this@MainActivity,"Successful",Toast.LENGTH_SHORT).show()
            override fun onResponse(call: Call<newsData>, response: Response<newsData>) {
                val resbody= response.body()!!
                    Adapter=MyAdapter(resbody.articles)
                    RecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                    RecyclerView.adapter = Adapter
                     Adapter.setItemClickListener(object : MyAdapter.onItemClickListener {
                         override fun onItemClick(position: Int) {
                            val intent=Intent(this@MainActivity,MainActivity2::class.java)
                             intent.putExtra("title",resbody.articles[position].title)
                             intent.putExtra("desc",resbody.articles[position].description)
                             intent.putExtra("cover",resbody.articles[position].urlToImage)
                             intent.putExtra("date",resbody.articles[position].publishedAt)
                             startActivity(intent)
                         }
                     })
            }

            override fun onFailure(call: Call<newsData>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_SHORT).show()
            }

        })
    }
}