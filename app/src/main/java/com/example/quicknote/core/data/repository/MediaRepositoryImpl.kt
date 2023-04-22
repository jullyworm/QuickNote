package com.example.quicknote.core.data.repository

import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File
import java.util.*
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val imagesDir: File,
    private val mimeTypeMap: MimeTypeMap,
) : MediaRepository {

    override fun saveImage(uri: Uri): String? {
        return contentResolver.openInputStream(uri)?.use{ inputStream ->
            val mimeType = contentResolver.getType(uri)
            val extension = mimeTypeMap.getExtensionFromMimeType(mimeType)
            val file = File(imagesDir, UUID.randomUUID().toString() + ".$extension").also{
                it.parentFile?.mkdirs()
                it.outputStream().use{ outputStream ->
                    inputStream.copyTo(outputStream)

                }
            }
            file.absolutePath
        }
    }

    override fun deleteImage(path: String) {
        File(path).delete()
    }

}