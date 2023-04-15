package com.example.quicknote.core.data

import com.example.quicknote.core.data.db.NoteDataBase
import com.example.quicknote.core.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseDataSource @Inject constructor(
    private val database: NoteDataBase,
){
    private val dao = database.dao()

    fun getNotes(): Flow<List<NoteEntity>> {
        return dao.getNotes()
    }

    fun getNoteById(id: Int): NoteEntity {
        return dao.getNoteById(id)
    }

    fun getLastNote() : NoteEntity{
        return dao.getLastNote()
    }

    fun saveNote(id: Int?, text: String){
        if (id == null) {
            dao.saveNote(NoteEntity(0, text))
        }else{
            dao.saveNote(NoteEntity(id, text))
        }
    }

    fun search(text: String) : Flow<List<NoteEntity>> {
        return dao.search(text)
    }
}