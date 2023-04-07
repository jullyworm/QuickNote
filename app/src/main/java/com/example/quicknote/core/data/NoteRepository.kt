package com.example.quicknote.core.data

import com.example.quicknote.core.domain.Note
import java.util.*

class NoteRepository {
    companion object {
        private val NOTES = mutableListOf<Note>(
            Note(
                id = "1",
                text = "Щука съела ацетат, получается цитрат"
            ),
        )
    }

    fun getNotes(): List<Note> {
        return NOTES
    }

    fun getNote(id: String) : Note {
        val index = NOTES.indexOfFirst { it.id == id }
        return NOTES[index]
    }

    fun saveNote(id: String?, text: String): List<Note> {
        if (id == null) {
            NOTES.add(
                Note(
                    id = UUID.randomUUID().toString(),
                    text = text
                )
            )
        } else {
            val index = NOTES.indexOfFirst { it.id == id }
            if (index >= 0) {
                NOTES[index] = NOTES[index].copy(
                    text = text,
                )
            }

        }
        return NOTES.toList()
    }
}