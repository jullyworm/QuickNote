package com.example.quicknote.core.presentation

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
class NoteViewModel @Inject constructor(
    private val saveNotesUseCase: SaveNotesUseCase,
    private val getOneNoteUseCase: GetOneNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val getLastNoteUseCase: GetLastNoteUseCase
) : ViewModel() {
    private val _noteLiveData = MutableLiveData<Note>()
    val noteLiveData: LiveData<Note> = _noteLiveData

    fun getNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _noteLiveData.postValue(getOneNoteUseCase(id))
        }
    }

    fun getAddedNote(){
        viewModelScope.launch(Dispatchers.IO) {
            _noteLiveData.postValue(getLastNoteUseCase())
        }
    }

    fun saveNote(id: Int, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveNotesUseCase(id, text)
        }
    }

    fun addNote(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase(text)
        }
    }
}
