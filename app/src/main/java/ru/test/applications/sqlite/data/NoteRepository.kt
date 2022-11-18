package ru.test.applications.sqlite.data

import androidx.lifecycle.LiveData
import ru.test.applications.sqlite.dto.Note

interface NoteRepository {
    val data: LiveData<List<Note>>

    fun save(note: Note)

    companion object {
        const val NEW_NOTE_ID = 0L
    }
}