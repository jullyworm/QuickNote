package com.example.quicknote.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quicknote.core.domain.GetNotesUseCase
import com.example.quicknote.core.domain.Note
import com.example.quicknote.core.domain.SaveNotesUseCase

class GridViewModel(
    private val getNotesUseCase: GetNotesUseCase = GetNotesUseCase(),
    private val saveNotesUseCase: SaveNotesUseCase = SaveNotesUseCase(),
) : ViewModel() {
    private val _notesLiveData = MutableLiveData<List<Note>> ()
    val notesLiveData : LiveData<List<Note>> = _notesLiveData

    fun getNotes() {
        _notesLiveData.value = getNotesUseCase()
    }

    fun addNote(text: String){
        _notesLiveData.value = saveNotesUseCase(null, text)
    }

    fun saveNote(id: String, text: String){
        _notesLiveData.value = saveNotesUseCase(id, text)
    }

}