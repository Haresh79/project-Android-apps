package com.example.memeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var meme:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        imageView=findViewById(R.id.imageView)
        textView=findViewById(R.id.textView)
        apiClient()
        findViewById<Button>(R.id.button2).setOnClickListener {
            nextMeme()
        }
        findViewById<Button>(R.id.button).setOnClickListener {
            shareMeme()
        }
    }
    private fun apiClient(){
        val RetrofitClient= Retrofit.Builder()
            .baseUrl("https://api.imgflip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val random = (0..100).random()

        val call= RetrofitClient.getMeme()
        call.enqueue(object : Callback<memedata>{
            override fun onResponse(call: Call<memedata>, response: Response<memedata>) {
                val responseBody= response.body()
                responseBody.let {
                     meme = it?.data?.memes?.get(random)?.url.toString()
                    val memetitle=it?.data?.memes?.get(random)?.name
                    Picasso.get().load(meme).into(imageView)
                    textView.text=memetitle
                }
            }

            override fun onFailure(call: Call<memedata>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Faild to load API",Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun nextMeme(){
        apiClient()
    }
    private fun shareMeme(){
        val sendIntent= Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT,meme)
        sendIntent.type="text/plain"
        val chooser= Intent.createChooser(sendIntent,"Share this meme")
        startActivity(chooser)

    }
}