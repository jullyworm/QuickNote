package com.example.quicknote.core.data.repository

import com.example.quicknote.core.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes() : Flow<List<Note>>

    fun getNote(id: Int) : Note
    fun getLastNote() : Note
    suspend fun saveNote(
        id: Int?,
        text: String
    )
    fun searchNotes(text: String) : Flow<List<Note>>
}