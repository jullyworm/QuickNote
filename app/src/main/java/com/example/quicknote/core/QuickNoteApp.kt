package com.example.quicknote.core

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

class QuickNoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


    companion object {
        lateinit var INSTANCE: QuickNoteApp
            private set
    }
}

val Context.dataStore by preferencesDataStore("notes")