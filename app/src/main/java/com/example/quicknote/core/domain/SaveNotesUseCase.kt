package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository

class SaveNotesUseCase {
    private val noteRepository: NoteRepository = NoteRepository()
    suspend operator fun invoke(
        id: Int?,
        text: String,
    ) {
        return noteRepository.saveNote(id, text)
    }
}