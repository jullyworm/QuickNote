package com.example.quicknote.core.domain

import com.example.quicknote.core.data.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetOneNoteUseCase {
    private val noteRepository : NoteRepository = NoteRepository()
    suspend operator fun invoke(id: String): String? {
        return noteRepository.getNote(id)
    }
}