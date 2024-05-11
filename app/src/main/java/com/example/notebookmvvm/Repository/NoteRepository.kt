package com.example.notebookmvvm.Repository

import com.example.notebookmvvm.database.NoteDatabase
import com.example.notebookmvvm.model.Note

class NoteRepository(private val db: NoteDatabase) {

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNote()
    fun searchNote(query: String) = db.getNoteDao().searchNote(query)

}