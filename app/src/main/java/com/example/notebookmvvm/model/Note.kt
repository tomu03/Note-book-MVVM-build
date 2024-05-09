package com.example.notebookmvvm.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Note(
    @PrimaryKey
    val id: Int,
    val noteTitle: String,
    val noteBody: String
) : Parcelable
