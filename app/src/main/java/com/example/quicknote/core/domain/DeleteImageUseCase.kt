package com.example.quicknote.core.domain

import com.example.quicknote.core.data.repository.MediaRepository
import javax.inject.Inject

class DeleteImageUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {
    operator fun invoke(imagePath: String) {
        mediaRepository.deleteImage(imagePath)

    }
}