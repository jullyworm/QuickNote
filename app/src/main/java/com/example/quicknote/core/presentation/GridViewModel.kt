package com.example.quicknote.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.core.domain.GetNotesUseCase
import com.example.quicknote.core.domain.Note
import com.example.quicknote.core.domain.SaveNotesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GridViewModel(
    private val getNotesUseCase: GetNotesUseCase = GetNotesUseCase(),
    private val saveNotesUseCase: SaveNotesUseCase = SaveNotesUseCase(),
) : ViewModel() {
    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase().collect { list ->
                _notesLiveData.value = list
            }
        }
    }
    fun addNote(text: String) {
        viewModelScope.launch {
            saveNotesUseCase(null, text)
        }
    }

    fun saveNote(id: String, text: String) {
        viewModelScope.launch {
            saveNotesUseCase(id, text)
        }
    }
}