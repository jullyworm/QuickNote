package com.example.quicknote.core.data.repository

import com.example.quicknote.core.data.DatabaseDataSource
import com.example.quicknote.core.data.NoteMapper
import com.example.quicknote.mapValue
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dataSource: DatabaseDataSource,
) : NoteRepository {

    override fun getNotes() = dataSource.getNotes().mapValue{
        NoteMapper.fromDbToModel(it)
    }

    override fun getNote(id: Int) = NoteMapper.fromDbToModel(dataSource.getNoteById(id))
    override fun getLastNote() = NoteMapper.fromDbToModel(dataSource.getLastNote())
    override suspend fun saveNote(
        id: Int?,
        text: String
    ) = dataSource.saveNote(id, text)

    override fun searchNotes(text: String) = dataSource.search(text).mapValue {
        NoteMapper.fromDbToModel(it)
    }
}