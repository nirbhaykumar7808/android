package com.carapps.notesapp.domain.repository

import kotlinx.coroutines.flow.Flow
import com.carapps.notesapp.data.database.Note

interface NoteRepository {

    suspend fun insert(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)

    fun getNotes():Flow<List<Note>>

    fun getNote(id: Int): Note
}

