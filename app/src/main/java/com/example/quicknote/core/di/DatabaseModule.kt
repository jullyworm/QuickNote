package com.example.quicknote.core.di

import android.content.Context
import androidx.room.Room
import com.example.quicknote.core.data.db.NoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(
        @ApplicationContext context: Context,
    ): NoteDataBase {
        return Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            "notes.db"
        ).build()
    }
}