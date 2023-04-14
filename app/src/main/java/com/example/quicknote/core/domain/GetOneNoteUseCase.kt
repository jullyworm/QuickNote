package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.NoteRepository
import javax.inject.Inject

class GetOneNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    operator fun invoke(id: Int): Note {
        return noteRepository.getNote(id)
    }
}