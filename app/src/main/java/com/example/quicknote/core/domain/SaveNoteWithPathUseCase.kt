package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.NoteRepository
import javax.inject.Inject

class SaveNoteWithPathUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(
        id: Int?,
        text: String,
        imagePath: String?,
    ) {
        return noteRepository.saveNote(id, text, imagePath)
    }
}