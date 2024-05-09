package com.example.notebookmvvm.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notebookmvvm.model.Note
import java.util.concurrent.locks.Lock

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        private val lock = Any()

        operator fun invoke(context: android.content.Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: android.content.Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note.db"
        ).build()
    }

}