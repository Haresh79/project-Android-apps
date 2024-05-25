package com.example.birthdaywish

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val selectpic = findViewById<Button>(R.id.buttonImg)
        val createcard =  findViewById<Button>(R.id.buttoncreate)
        val name = findViewById<TextView>(R.id.editTextText)

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                createcard.setOnClickListener {
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("name", name.text.toString())
                    intent.putExtra("uri", uri.toString())
                    startActivity(intent)
                }
            }
        }
        selectpic.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }

    }


}