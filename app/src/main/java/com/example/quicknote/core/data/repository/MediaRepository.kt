package com.example.quicknote.core.data.repository

import android.net.Uri

interface MediaRepository {

    fun saveImage(uri: Uri): String?

    fun deleteImage(path: String)
}