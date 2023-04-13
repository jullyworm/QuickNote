package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository
import kotlinx.coroutines.flow.Flow

class SearchNotesUseCase {
    private val noteRepository : NoteRepository = NoteRepository()
    operator fun invoke(text: String): Flow<List<Note>> {
        return noteRepository.searchNotes(text)
    }
}