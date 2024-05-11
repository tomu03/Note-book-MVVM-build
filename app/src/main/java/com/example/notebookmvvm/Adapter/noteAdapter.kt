package com.example.notebookmvvm.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.notebookmvvm.databinding.NoteLayoutBinding
import com.example.notebookmvvm.model.Note
import java.lang.Math.random
import java.util.Random


class NoteAdapter :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itembinding: ViewBinding) : RecyclerView.ViewHolder(itembinding.root) {
    }

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.noteTitle == newItem.noteTitle && oldItem.noteBody == newItem.noteBody
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        val random = random()
        val colour = Color.argb(
            255,
            random.256, random, random)

        holder.itembinding.apply {
            noteTitle.text = currentNote.noteTitle
            noteBody.text = currentNote.noteBody
            ibcolor.setBackgroundColor(colour)
        }
    }
}