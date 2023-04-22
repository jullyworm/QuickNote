package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note: Note) {
        return noteRepository.deleteNote(note)
    }
}