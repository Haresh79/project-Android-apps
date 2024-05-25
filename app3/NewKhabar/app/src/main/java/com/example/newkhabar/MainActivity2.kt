package com.example.newkhabar

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val title=intent.getStringExtra("title")
        val desc=intent.getStringExtra("desc")
        val cover=intent.getStringExtra("cover")
        val date=intent.getStringExtra("date")

        val tvTitle=findViewById<TextView>(R.id.textView)
        val tvDesc=findViewById<TextView>(R.id.textView2)
        val tvDate=findViewById<TextView>(R.id.textView3)
        val coverimg=findViewById<ImageView>(R.id.imageView2)

        tvTitle.text=title
        tvDesc.text=desc
        tvDate.text=date
        Picasso.get().load(cover).into(coverimg)
    }
}