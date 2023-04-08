package com.example.quicknote.core.data

import com.example.quicknote.core.domain.Note
import java.util.*

class NoteRepository(
    private val dataSource: DataSource = DataSource(),
) {

    fun getNotes() = dataSource.getNotes()

    suspend fun getNote(id: String) = dataSource.getNote(id)
    suspend fun addNote(text: String) = dataSource.addNote(text)
    suspend fun saveNote(
        id: String?,
        text: String
    ) = dataSource.saveNote(id, text)
}