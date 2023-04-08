package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase {

    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}