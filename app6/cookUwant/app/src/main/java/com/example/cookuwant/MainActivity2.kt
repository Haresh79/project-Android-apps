package com.example.cookuwant

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private lateinit var database: DatabaseReference
//    private lateinit var poster:String
//    private lateinit var ingredents:String
//    private lateinit var video:String
//    private lateinit var s1:String
//    private lateinit var s2:String
//    private lateinit var s3:String
//    private lateinit var s4:String
//    private lateinit var s5:String
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val itemName= intent.getStringExtra("itemName")
        database= FirebaseDatabase.getInstance().getReference("recipes")
//        getData(itemName)
        database.child(itemName.toString()).get().addOnSuccessListener {
            val poster= it.child("poster").value.toString()
           val ingredents= it.child("ingredients").value.toString()
            val video= it.child("video_link").value.toString()
           val s1=it.child("Steps").child("1").value.toString()
           val s2=it.child("Steps").child("2").value.toString()
           val s3=it.child("Steps").child("3").value.toString()
           val s4=it.child("Steps").child("4").value.toString()
           val s5=it.child("Steps").child("5").value.toString()

            findViewById<TextView>(R.id.foodName).text=itemName
            val posterImg=findViewById<ImageView>(R.id.itemImg)
            Picasso.get().load(poster.toString()).into(posterImg)
            findViewById<TextView>(R.id.ingredents).text=ingredents.toString()
            val webView=findViewById<WebView>(R.id.webview)
            webviewSetup(webView,video)
            findViewById<TextView>(R.id.s1).text="1. "+s1.toString()
            findViewById<TextView>(R.id.s2).text="2. "+s2.toString()
            findViewById<TextView>(R.id.s3).text="3. "+s3.toString()
            findViewById<TextView>(R.id.s4).text="4. "+s4.toString()
            findViewById<TextView>(R.id.s5).text="5. "+s5.toString()
        }



    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun webviewSetup(webView: WebView?, video: String) {
        if (webView != null) {
            webView.webViewClient= WebViewClient()
        }
        webView?.apply {
            settings.javaScriptEnabled=true
            settings.safeBrowsingEnabled=true
            loadUrl("${video}")
        }

    }
}