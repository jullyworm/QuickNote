package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository

class GetLastNoteUseCase {
    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(): Note {
        return noteRepository.getLastNote()
    }
}