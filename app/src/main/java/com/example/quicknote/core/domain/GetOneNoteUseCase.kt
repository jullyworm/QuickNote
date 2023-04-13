package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetOneNoteUseCase {
    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(id: Int): Note {
        return noteRepository.getNote(id)
    }
}