package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository

class AddNoteUseCase {
    private val noteRepository: NoteRepository = NoteRepository()
    suspend operator fun invoke(text: String) : Note {
        return noteRepository.addNote(text)
    }
}