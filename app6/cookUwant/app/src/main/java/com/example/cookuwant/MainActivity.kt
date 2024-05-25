package com.example.cookuwant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    val foodlist= mutableListOf<String>()
    val arrayList=ArrayList<fooditem>()
    lateinit var myRecyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance().getReference("recipes")
        getdata(database)
        findViewById<Button>(R.id.uploadpage).setOnClickListener {
            val intent2= Intent(this, MainActivity3::class.java)
            intent2.putExtra("count",arrayList.size)
            startActivity(intent2)
        }
    }
    private fun getdata(database: DatabaseReference) {
        database.get().addOnSuccessListener { dataSnapshot ->
            dataSnapshot.child("name").children.forEach { nameSnapshot ->
                val name = nameSnapshot.getValue(String::class.java)
                name?.let {
                    foodlist.add(it)
                    val poster = dataSnapshot.child(it).child("poster").getValue(String::class.java)!!
                    val title = it
                    arrayList.add(fooditem(poster, title))
                }
            }
            myRecyclerView=findViewById<RecyclerView>(R.id.rv)
            myRecyclerView.layoutManager=LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            val Adapter=myAdapter(arrayList)
            myRecyclerView.adapter=Adapter
            Adapter.setItemClickListener(object : myAdapter.onItemClickListener{
                override fun onItemClicked(position: Int) {
                    val foodName=foodlist.get(position)
                    val intent= Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("itemName", foodName)
                    startActivity(intent)
                }

            })
        }.addOnFailureListener { exception ->
            // Handle any errors that may occur during data retrieval
            Log.e("MainActivity", "Error getting data", exception)
        }
    }
}