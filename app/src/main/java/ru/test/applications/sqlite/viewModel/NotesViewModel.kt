package ru.test.applications.sqlite.viewModel

import android.app.Application
import androidx.lifecycle.*
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

    val filterData = MutableLiveData<List<Note>>()

    fun onSearch(title: String) {
        filterData.value = data.value?.filter { it.title.startsWith(title) }
    }

    fun onCreateNewNote(note: Note) {
        repository.save(note)
    }
}