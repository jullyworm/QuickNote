package com.example.quicknote.core.data

import com.example.quicknote.core.data.db.NoteEntity
import com.example.quicknote.core.domain.Note

object NoteMapper {

    fun fromModelToDb(model: Note) = NoteEntity(
        id = model.id,
        text = model.text
    )

    fun fromDbToModel(db: NoteEntity) = Note(
        id = db.id,
        text = db.text
    )
}