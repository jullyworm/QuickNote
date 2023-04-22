package com.example.quicknote.core.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.core.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val saveNotesUseCase: SaveNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getOneNoteUseCase: GetOneNoteUseCase,
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
            saveNotesUseCase(null, text, null)
        }
    }

    fun saveNote(id: Int, text: String, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            saveNotesUseCase(id, text, uri)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase(getOneNoteUseCase(id))
        }
    }
}