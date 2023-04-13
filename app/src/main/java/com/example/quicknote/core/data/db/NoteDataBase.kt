package com.example.quicknote.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1,
)


abstract class NoteDataBase: RoomDatabase() {

    abstract fun dao(): NoteDAO
}