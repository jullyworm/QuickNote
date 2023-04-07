package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository

class GetOneNoteUseCase {
    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(id: String):Note {
        return noteRepository.getNote(id)
    }
}