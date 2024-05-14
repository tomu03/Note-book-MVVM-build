package com.example.notebookmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notebookmvvm.Adapter.NoteAdapter
import com.example.notebookmvvm.Repository.NoteRepository
import com.example.notebookmvvm.database.NoteDatabase
import com.example.notebookmvvm.databinding.FragmentHomeBinding
import com.example.notebookmvvm.viewmodel.NoteViewModel
import com.example.notebookmvvm.viewmodel.NoteViewModelFactory


class homeFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteRepository: NoteRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val noteDatabase = NoteDatabase(requireContext())
        val repository = NoteRepository(noteDatabase)
        val factory =  NoteViewModelFactory(requireActivity().application,repository)
        noteViewModel = ViewModelProvider(this,factory).get(NoteViewModel::class.java)

        val adapter = NoteAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        noteViewModel.getAllNotes().observe(viewLifecycleOwner,{
            notes -> notes?.let {
                adapter.differ.submitList(it)
            if (it.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.noNoteAvailable.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.noNoteAvailable.visibility = View.GONE
            }
        }
        })

        binding.faBtn.setOnClickListener{
            val action = homeFragmentDirections.actionHomeFragmentToNewNoteFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

}