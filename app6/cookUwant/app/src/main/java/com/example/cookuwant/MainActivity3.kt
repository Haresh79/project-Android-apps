package com.example.cookuwant

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    var count=0
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        databaseReference= FirebaseDatabase.getInstance().getReference("recipes")
        count=intent.getIntExtra("count",5000)

        dialog=Dialog(this)
        dialog.setContentView(R.layout.custom_help)
        dialog.findViewById<Button>(R.id.closebtn).setOnClickListener {
            dialog.dismiss()
            dialog.findViewById<VideoView>(R.id.videoView).pause()
        }
        dialog.findViewById<VideoView>(R.id.videoView).setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.helpvideo))

        val user= findViewById<EditText>(R.id.userName)
        val itemName= findViewById<EditText>(R.id.itemName)
        val ingredients= findViewById<EditText>(R.id.itemIngredents)
        val video= findViewById<EditText>(R.id.videoLink)
        val poster= findViewById<EditText>(R.id.imageLink)
        val s1= findViewById<EditText>(R.id.st1)
        val s2= findViewById<EditText>(R.id.st2)
        val s3= findViewById<EditText>(R.id.st3)
        val s4= findViewById<EditText>(R.id.st4)
        val s5= findViewById<EditText>(R.id.st5)

        findViewById<Button>(R.id.upload).setOnClickListener {
             setdata(databaseReference,user,itemName,ingredients,video,poster,s1,s2,s3,s4,s5)
            user.text.clear()
            itemName.text.clear()
            ingredients.text.clear()
            video.text.clear()
            poster.text.clear()
            s1.text.clear()
            s2.text.clear()
            s3.text.clear()
            s4.text.clear()
            s5.text.clear()
        }
        findViewById<ImageView>(R.id.helpicon).setOnClickListener {
            dialog.show()
            dialog.findViewById<VideoView>(R.id.videoView).start()
        }
    }

    private fun setdata(
        databaseReference: DatabaseReference,
        user: EditText?,
        itemName: EditText?,
        ingredients: EditText?,
        video: EditText?,
        poster: EditText?,
        s1: EditText?,
        s2: EditText?,
        s3: EditText?,
        s4: EditText?,
        s5: EditText?
    ) {

        databaseReference.child(itemName?.text.toString()).child("writer").setValue(user?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("ingredients").setValue(ingredients?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("poster").setValue(poster?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("video_link").setValue(video?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("Steps").child("1").setValue(s1?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("Steps").child("2").setValue(s2?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("Steps").child("3").setValue(s3?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("Steps").child("4").setValue(s4?.text.toString())
        databaseReference.child(itemName?.text.toString()).child("Steps").child("5").setValue(s5?.text.toString())
        databaseReference.child("name").child("${count+1}").setValue(itemName?.text.toString())
        count++
    }
}