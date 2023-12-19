package com.carapps.notesapp.data.modules

import android.content.Context
import androidx.room.Room
import com.carapps.notesapp.data.database.NoteDatabase
import com.carapps.notesapp.data.repository.NoteRepositoryImpl
import com.carapps.notesapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providedDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun providedNoteRepository(
        noteDatabase: NoteDatabase
    ): NoteRepository = NoteRepositoryImpl(noteDatabase)
}




