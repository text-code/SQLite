package ru.test.applications.sqlite.dbCourseMaxim

import ru.test.applications.sqlite.dto.Note

interface NoteDao {
    fun save(note: Note): Note
    fun getAll(): List<Note>
}