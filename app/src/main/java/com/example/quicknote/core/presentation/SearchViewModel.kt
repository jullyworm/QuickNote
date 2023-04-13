package com.example.quicknote.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.core.domain.GetNotesUseCase
import com.example.quicknote.core.domain.Note
import com.example.quicknote.core.domain.SaveNotesUseCase
import com.example.quicknote.core.domain.SearchNotesUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getNotesUseCase: GetNotesUseCase = GetNotesUseCase(),
    private val searchNotesUseCase: SearchNotesUseCase = SearchNotesUseCase(),
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
    fun searchNotes(text: String) {
        viewModelScope.launch {
            searchNotesUseCase(text).collect{ list ->
                _notesLiveData.value = list
            }
        }
    }
}