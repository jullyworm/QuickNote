package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository

class SaveNotesUseCase {
    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(
        id: String?,
        text: String,
    ): List<Note> {
        return noteRepository.saveNote(id, text)
    }
}