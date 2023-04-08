package com.example.quicknote.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.core.domain.*
import kotlinx.coroutines.launch

class NoteViewModel(
    private val saveNotesUseCase: SaveNotesUseCase = SaveNotesUseCase(),
    private val getOneNoteUseCase: GetOneNoteUseCase = GetOneNoteUseCase(),
    private val addNoteUseCase: AddNoteUseCase = AddNoteUseCase()
) : ViewModel() {
    private val _noteLiveData = MutableLiveData<Note>()
    val noteLiveData: LiveData<Note> = _noteLiveData

    fun getNote(id: String) {
        viewModelScope.launch {
            _noteLiveData.value = getOneNoteUseCase(id)?.let { Note(id, it) }
        }
    }

    fun saveNote(id: String, text: String) {
        viewModelScope.launch {
            saveNotesUseCase(id, text)
        }
    }

    fun addNote(text: String) {
        viewModelScope.launch {
            _noteLiveData.value = addNoteUseCase(text)!!
        }
    }
}
