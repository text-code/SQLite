package ru.test.applications.sqlite.dbCourseMaxim

import android.database.Cursor
import ru.test.applications.sqlite.dto.Note

fun Cursor.toNote() = Note(
    id = getLong(getColumnIndexOrThrow(NotesTable.Column.ID.columnName)),
    title = getString(getColumnIndexOrThrow(NotesTable.Column.TITLE.columnName)),
    content = getString(getColumnIndexOrThrow(NotesTable.Column.CONTENT.columnName))
)