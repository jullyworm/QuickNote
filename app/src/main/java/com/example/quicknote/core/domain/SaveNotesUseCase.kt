package com.example.quicknote.core.domain

import android.net.Uri
import com.example.quicknote.core.data.repository.MediaRepository
import com.example.quicknote.core.data.repository.NoteRepository
import javax.inject.Inject

class SaveNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val mediaRepository: MediaRepository
) {
    suspend operator fun invoke(
        id: Int?,
        text: String,
        imageUri: Uri?,
    ) {
        if (imageUri == null) {
            return noteRepository.saveNote(id, text, null)
        }
        val imagePath = imageUri.let(mediaRepository::saveImage)
        return noteRepository.saveNote(id, text, imagePath)
    }
}