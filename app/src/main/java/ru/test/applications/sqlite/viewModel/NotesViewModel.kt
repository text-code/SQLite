package ru.test.applications.sqlite.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.test.applications.sqlite.data.NoteRepository
import ru.test.applications.sqlite.data.impl.SQLiteRepository
import ru.test.applications.sqlite.dbCourseMaxim.AppDb
import ru.test.applications.sqlite.dto.Note

class NotesViewModel(
    application: Application
) : AndroidViewModel(application) {

    // Подключение репозитория из курса
    private val repository: NoteRepository = SQLiteRepository(
        AppDb.getInstance(application).noteDao
    )
    val data by repository::data

    fun onCreateNewNote(note: Note) {
        repository.save(note)
    }
}