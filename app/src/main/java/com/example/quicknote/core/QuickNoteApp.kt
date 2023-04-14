package com.example.quicknote.core

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.quicknote.core.data.db.NoteDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuickNoteApp : Application()

val Context.dataStore by preferencesDataStore("notes")