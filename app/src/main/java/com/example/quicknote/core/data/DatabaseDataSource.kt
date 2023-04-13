package com.example.quicknote.core.data

import com.example.quicknote.core.QuickNoteApp
import com.example.quicknote.core.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow

class DatabaseDataSource {
    private val dao = QuickNoteApp.database.dao()

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