package com.example.noteit

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: notesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor=resources.getColor(R.color.black)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val addBtn= findViewById<Button>(R.id.button)
        val noteTxt=findViewById<EditText>(R.id.editTextText)

        val recyclerView=findViewById<RecyclerView>(R.id.RV)
        recyclerView.layoutManager=LinearLayoutManager(this)


        database= Room.databaseBuilder(applicationContext, notesDatabase::class.java, "notesDB").build()
        addBtn.setOnClickListener {
            val notetoadd=noteTxt?.text
            GlobalScope.launch {
                    database.noteDao().insert(note(0, notetoadd.toString()))
                    notetoadd?.clear()
                }
        }
        database.noteDao().getall().observe(this, {
            var Adapter= MyAdapter(ArrayList(it))
            recyclerView.adapter=Adapter
            Adapter.setItemClickListener(object :MyAdapter.onItemClickListener{
                override fun onItemClicked(note: note) {
                    deletenote(note)
                }
            })
        })

    }

    fun deletenote(note: note)= GlobalScope.launch(Dispatchers.IO){
        database.noteDao().delete(note)
    }
}