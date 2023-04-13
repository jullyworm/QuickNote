package com.example.quicknote.core

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.quicknote.core.data.db.NoteDataBase

class QuickNoteApp : Application() {


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


    companion object {
        lateinit var INSTANCE: QuickNoteApp
            private set
        val database by lazy {
            Room.databaseBuilder(
                INSTANCE,
                NoteDataBase::class.java,
                "notes.db"
            ).build()
        }
    }
}

val Context.dataStore by preferencesDataStore("notes")