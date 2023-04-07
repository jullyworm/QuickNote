package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository

class GetNotesUseCase {

    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(): List<Note> {
        return noteRepository.getNotes()
    }
}