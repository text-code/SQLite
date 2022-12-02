package ru.test.applications.sqlite.data.impl

import androidx.lifecycle.MutableLiveData
import ru.test.applications.sqlite.data.NoteRepository
import ru.test.applications.sqlite.dbCourseMaxim.NoteDao
import ru.test.applications.sqlite.dto.Note

class SQLiteRepository(
    private val dao: NoteDao
) : NoteRepository {

    private val notes
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override val data = MutableLiveData(dao.getAll())

    override fun save(note: Note) {
        val saved = dao.save(note)
        data.value = listOf(saved) + notes
    }
}