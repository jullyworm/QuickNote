package com.example.quicknote.core.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    fun getNoteById(id: Int) : NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC LIMIT 1")
    fun getLastNote() : NoteEntity

    @Query("SELECT * FROM notes WHERE text LIKE '%' || :query || '%' ORDER BY id DESC")
    fun search(query: String) : Flow<List<NoteEntity>>

    @Delete
    fun deleteNote(note: NoteEntity)
}