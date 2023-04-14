package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.NoteRepository
import javax.inject.Inject

class GetLastNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    operator fun invoke(): Note {
        return noteRepository.getLastNote()
    }
}