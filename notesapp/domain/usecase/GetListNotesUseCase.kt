package com.carapps.notesapp.domain.usecase

import com.carapps.notesapp.data.database.Note
import com.carapps.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}
