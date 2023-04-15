package com.example.quicknote.core

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuickNoteApp : Application()

val Context.dataStore by preferencesDataStore("notes")