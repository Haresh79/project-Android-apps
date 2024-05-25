package com.example.noteit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface noteDao {
    @Insert
    suspend fun insert(note: note)
    @Delete
    suspend fun delete(note: note)
    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun deleteNote(id:Long)
    @Query("SELECT * FROM notes")
    fun getall():LiveData<List<note>>
}