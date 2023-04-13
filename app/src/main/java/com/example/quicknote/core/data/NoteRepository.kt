package com.example.quicknote.core.data

import com.example.quicknote.core.data.db.NoteEntity
import com.example.quicknote.mapValue

class NoteRepository(
    private val dataSource: DatabaseDataSource = DatabaseDataSource(),
) {

    fun getNotes() = dataSource.getNotes().mapValue{
        NoteMapper.fromDbToModel(it)
    }

    fun getNote(id: Int) =  NoteMapper.fromDbToModel(dataSource.getNoteById(id))
    fun getLastNote() = NoteMapper.fromDbToModel(dataSource.getLastNote())
    suspend fun saveNote(
        id: Int?,
        text: String
    ) = dataSource.saveNote(id, text)

    fun searchNotes(text: String) = dataSource.search(text).mapValue {
        NoteMapper.fromDbToModel(it)
    }
}