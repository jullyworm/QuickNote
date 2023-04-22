package com.example.quicknote.core.di

import com.example.quicknote.core.data.repository.MediaRepository
import com.example.quicknote.core.data.repository.MediaRepositoryImpl
import com.example.quicknote.core.data.repository.NoteRepository
import com.example.quicknote.core.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMediaRepository(
        impl: MediaRepositoryImpl
    ): MediaRepository

    @Binds
    fun bindNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository
}