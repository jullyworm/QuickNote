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
class NoteViewModel @Inject constructor(
    private val saveNotesUseCase: SaveNotesUseCase,
    private val saveNoteWithPathUseCase: SaveNoteWithPathUseCase,
    private val getOneNoteUseCase: GetOneNoteUseCase,
    private val getLastNoteUseCase: GetLastNoteUseCase,
    private val deleteImageUseCase: DeleteImageUseCase
) : ViewModel() {
    private val _noteLiveData = MutableLiveData<Note>()
    val noteLiveData: LiveData<Note> = _noteLiveData
    private val _imageLiveData = MutableLiveData<Uri?>()
    val imageLiveData: LiveData<Uri?> = _imageLiveData

    fun getNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _noteLiveData.postValue(getOneNoteUseCase(id))
        }
    }

    fun getAddedNote() {
        viewModelScope.launch(Dispatchers.IO) {
            _noteLiveData.postValue(getLastNoteUseCase())
        }
    }

    fun updateNote() {
        viewModelScope.launch(Dispatchers.IO) {
            _noteLiveData.postValue(_noteLiveData.value)
        }
    }

    fun deleteImage() {
        viewModelScope.launch(Dispatchers.IO) {
            _imageLiveData.postValue(null)
            _noteLiveData.value?.imagePath?.let { deleteImageUseCase(it) }
            val id = _noteLiveData.value?.id
            val text = _noteLiveData.value?.text!!
            saveNotesUseCase(id, text, null)
        }

    }

    fun setUri(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            _imageLiveData.postValue(uri)
        }
    }

    fun saveImage(id: Int, uri: Uri?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id == 0) {
                val lastId = getLastNoteUseCase().id
                val text = getLastNoteUseCase().text
                saveNotesUseCase(lastId, text, uri)
            } else {
                val text = getOneNoteUseCase(id).text
                saveNotesUseCase(id, text, uri)
            }
        }
    }

    fun saveNote(id: Int, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (getOneNoteUseCase(id).imagePath != null) {
                saveNoteWithPathUseCase(id, text, getOneNoteUseCase(id).imagePath)
            } else {
                val uri = _imageLiveData.value
                saveNotesUseCase(id, text, uri)
            }

        }
    }
}
