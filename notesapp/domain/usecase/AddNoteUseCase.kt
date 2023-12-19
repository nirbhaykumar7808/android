package com.carapps.notesapp.domain.usecase

import com.carapps.notesapp.data.database.Note
import com.carapps.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend fun invoke(note: Note) {
        noteRepository.insert(note)
    }
}
