package com.example.noteit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class note(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val note:String
)
