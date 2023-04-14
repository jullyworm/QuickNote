package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    operator fun invoke(text: String): Flow<List<Note>> {
        return noteRepository.searchNotes(text)
    }
}