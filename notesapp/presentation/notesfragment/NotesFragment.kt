package com.carapps.notesapp.presentation.notesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.carapps.notesapp.databinding.FragmentNotesBinding
import com.carapps.notesapp.presentation.notesfragment.adapters.NoteAdapter
import com.carapps.notesapp.presentation.notesfragment.noteviewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentNotesBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteAdapter {
            val action =
                NotesFragmentDirections.actionNoteListFragmentToNoteDetailFragment()
            val bundle = bundleOf("id" to it.id)
            this.findNavController().navigate(action.actionId, bundle)
        }

        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.notes.collect { adapter.submitList(it) }
        }

        binding.apply {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.setHasFixedSize(true)

            fab.setOnClickListener {
                val action =
                    NotesFragmentDirections.actionNoteListFragmentToNoteDetailFragment()
                findNavController().navigate(action)
            }
        }
    }
}
