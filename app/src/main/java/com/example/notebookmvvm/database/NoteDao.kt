package com.example.notebookmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notebookmvvm.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
    @Query("SELECT *FROM note_table ORDER BY id DESC")
    fun getAllNote():LiveData<List<Note>>

    @Query("SELECT *FROM note_table WHERE noteTitle LIKE :query OR noteBody LIKE :query")
    fun searchAllNote(query: String):LiveData<List<Note>>

}