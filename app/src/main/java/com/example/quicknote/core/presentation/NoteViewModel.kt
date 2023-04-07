package com.example.quicknote.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quicknote.core.domain.*

class NoteViewModel(
    private val saveNotesUseCase: SaveNotesUseCase = SaveNotesUseCase() ,
    private val getOneNoteUseCase: GetOneNoteUseCase = GetOneNoteUseCase(),
) : ViewModel() {
    private val _noteLiveData = MutableLiveData<Note> ()
    val noteLiveData : LiveData<Note> = _noteLiveData

    fun getNote(id: String) {
        _noteLiveData.value = getOneNoteUseCase(id)
    }

    fun saveNote(id: String, text: String) {
        val list : List<Note> = saveNotesUseCase(id,text)
        val index = list.indexOfFirst { it.id == id }
        _noteLiveData.value = list[index]
    }

    fun addNote(text: String) {
        _noteLiveData.value = saveNotesUseCase(null,text).last()
    }
}
