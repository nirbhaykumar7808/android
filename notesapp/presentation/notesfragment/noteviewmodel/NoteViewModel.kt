package com.carapps.notesapp.presentation.notesfragment.noteviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carapps.notesapp.data.database.Note
import com.carapps.notesapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    var notes: Flow<List<Note>> = noteRepository.getNotes()

    private fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insert(note)
        }
    }

    private fun getNewNoteEntry(noteTitle: String, noteText: String, noteTime: String): Note {
        return Note(
            id = 0,
            noteTitle = noteTitle,
            description = noteText,
            timeStamp = noteTime
        )
    }

    fun addNewNote(noteTitle: String, noteText: String, noteTime: String) {
        val newNote = getNewNoteEntry(noteTitle, noteText, noteTime)
        insertNote(newNote)
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.update(note)
        }
    }

    private fun getUpdatedNoteEntry(noteId: Int, noteTitle: String, noteText: String, noteTime: String): Note {
        return Note(
            id = noteId,
            noteTitle = noteTitle,
            description = noteText,
            timeStamp = noteTime
        )
    }

    fun updateNote(noteId: Int, noteTitle: String, noteText: String, noteTime: String) {
        val updatedNote = getUpdatedNoteEntry(noteId, noteTitle, noteText, noteTime)
        updateNote(updatedNote)
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }

    fun retrieveNote(id: Int) = noteRepository.getNote(id)

    fun isEntryValid(noteTitle: String, noteText: String): Boolean {
        if (noteTitle.isBlank() || noteText.isBlank())
            return false
        return true
    }
}
