package com.example.quicknote.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.core.domain.GetNotesUseCase
import com.example.quicknote.core.domain.Note
import com.example.quicknote.core.domain.SaveNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val saveNotesUseCase: SaveNotesUseCase,
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
        viewModelScope.launch(Dispatchers.IO) {
            saveNotesUseCase(null, text)
        }
    }

    fun saveNote(id: Int, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveNotesUseCase(id, text)
        }
    }
}