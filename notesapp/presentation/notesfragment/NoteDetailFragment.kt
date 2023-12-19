package com.carapps.notesapp.presentation.notesfragment

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.carapps.notesapp.R
import com.carapps.notesapp.databinding.FragmentNoteDetailBinding
import com.carapps.notesapp.data.database.Note
import com.carapps.notesapp.presentation.notesfragment.noteviewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private var mBinding: FragmentNoteDetailBinding? = null
    private val binding get() = mBinding!!

    lateinit var note: Note

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenuProvider()

        if (requireArguments().get("id") != null) {
            val id = requireArguments().get("id") as Int
            viewModel.retrieveNote(id).let { selectedNote ->
                note = selectedNote
                bind(note)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    private fun initMenuProvider() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.item_detail_action_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.delete -> if (::note.isInitialized) viewModel.deleteNote(note)
                    R.id.insert -> saveEntry()
                    else -> saveEntry()
                }
                findNavController().navigateUp()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun bind(note: Note) {
        binding.apply {
            noteTitle.setText(note.noteTitle)
            noteText.setText(note.description)
        }
    }

    private fun saveEntry() {
        val noteTitle = binding.noteTitle.text.toString()
        val noteText = binding.noteText.text.toString()
        val noteTime = SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.ENGLISH)
            .format(Date())
        if (viewModel.isEntryValid(noteTitle, noteText)) {
            if (::note.isInitialized) {
                viewModel.updateNote(note.id, noteTitle, noteText, noteTime)
            } else {
                viewModel.addNewNote(noteTitle, noteText, noteTime)
            }
        }
    }
}
