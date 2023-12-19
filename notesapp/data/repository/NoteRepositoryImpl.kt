package com.carapps.notesapp.data.repository

import com.carapps.notesapp.data.database.NoteDatabase
import com.carapps.notesapp.data.database.Note
import com.carapps.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDatabase: NoteDatabase
): NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDatabase.noteDao().getNotes()
    }

    override suspend fun insert(note: Note) {
        noteDatabase.noteDao().insert(note)
    }

    override suspend fun update(note: Note) {
        noteDatabase.noteDao().update(note)
    }

    override suspend fun delete(note: Note) {
        noteDatabase.noteDao().delete(note)
    }

    override fun getNote(id: Int): Note {
        return noteDatabase.noteDao().getNote(id)
    }
}
