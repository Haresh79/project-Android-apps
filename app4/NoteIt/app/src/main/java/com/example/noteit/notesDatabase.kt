package com.example.noteit

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [note::class], version = 1)
abstract class notesDatabase:RoomDatabase() {
    abstract fun noteDao():noteDao
}