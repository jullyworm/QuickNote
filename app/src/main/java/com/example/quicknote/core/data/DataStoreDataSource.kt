package com.example.quicknote.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.quicknote.core.QuickNoteApp
import com.example.quicknote.core.dataStore
import com.example.quicknote.core.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import java.util.*
import javax.inject.Inject

class DataStoreDataSource @Inject constructor(
    private val dataStore : DataStore <Preferences>
){

    fun getNotes(): Flow<List<Note>> {
        return dataStore.data.map { preferences ->
            preferences.asMap().map { (id, text) ->
                Note(id.name.toInt(), text.toString())
            }
        }
    }

    suspend fun getNote(id: String): String? {
        return dataStore.data.first()[stringPreferencesKey(id)]
    }


    fun getNoteFlow(id: String): Flow<String> {
        return dataStore.data.mapNotNull {
            it[stringPreferencesKey(id)]
        }
    }

    //неприятный костыль для того, чтобы заметка создавалась нормально
   /* suspend fun addNote(text: String): Note {
        val id = UUID.randomUUID()
        dataStore.updateData {
            it.toMutablePreferences().apply {
                set(
                    key = stringPreferencesKey(id),
                    value = text,
                )
            }.toPreferences()
        }
        return Note (id, text)
    }*/

    suspend fun saveNote(id: Int?, text: String) {
        if (id == null) {
            dataStore.updateData {
                it.toMutablePreferences().apply {
                    set(
                        key = stringPreferencesKey(UUID.randomUUID().toString()),
                        value = text,
                    )
                }.toPreferences()
            }
        } else {
            val key = stringPreferencesKey(id.toString())
            dataStore.updateData {
                if (it.contains(key)) {
                    it.toMutablePreferences().apply {
                        set(
                            key = key,
                            value = text,
                        )
                    }.toPreferences()
                } else {
                    it
                }
            }
        }
    }
}