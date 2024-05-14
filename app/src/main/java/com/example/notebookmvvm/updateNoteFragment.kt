package com.example.notebookmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notebookmvvm.Repository.NoteRepository
import com.example.notebookmvvm.database.NoteDatabase
import com.example.notebookmvvm.databinding.FragmentUpdateNoteBinding
import com.example.notebookmvvm.model.Note
import com.example.notebookmvvm.viewmodel.NoteViewModel
import com.example.notebookmvvm.viewmodel.NoteViewModelFactory


class updateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private var currentNote: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        val noteDatabase = NoteDatabase(requireContext())
        val repository = NoteRepository(noteDatabase)
        val factory = NoteViewModelFactory(requireActivity().application, repository)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        arguments?.let {
            currentNote = updateNoteFragmentArgs.fromBundle(it).note
            currentNote?.let { note ->
                binding.noteTitleEt.setText(note.noteTitle)
                binding.noteBodyEt.setText(note.noteBody)
            }
        }

        binding.fabBtn.setOnClickListener {
            val noteTitle = binding.noteTitleEt.text.toString().trim()
            val noteBody = binding.noteBodyEt.text.toString().trim()

            if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
                currentNote?.let { note ->
                    val updatedNote = note.copy(noteTitle = noteTitle, noteBody = noteBody)
                    noteViewModel.updateNote(updatedNote)
                    requireActivity().onBackPressed()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter a note title and body", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}