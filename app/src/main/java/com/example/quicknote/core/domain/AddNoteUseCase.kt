package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
    ) {
    suspend operator fun invoke(text: String) {
        return noteRepository.saveNote(null, text)
    }
}