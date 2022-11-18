package ru.test.applications.sqlite.dbCourseMaxim

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import ru.test.applications.sqlite.dto.Note

class NoteDaoImpl(
    private val db: SQLiteDatabase
) : NoteDao {

    override fun getAll() = db.query(
        NotesTable.NAME,
        NotesTable.ALL_COLUMN_NAME,
        null,
        null,
        null,
        null,
        "${NotesTable.Column.ID.columnName} DESC"
    ).use { cursor ->
        List(cursor.count) {
            cursor.moveToNext()
            cursor.toNote()
        }
    }

    override fun save(note: Note): Note {
        val values = ContentValues().apply {
            put(NotesTable.Column.TITLE.columnName, note.title)
            put(NotesTable.Column.CONTENT.columnName, note.content)
        }
        val id = db.insert(NotesTable.NAME, null, values)

        db.query(
            NotesTable.NAME,
            NotesTable.ALL_COLUMN_NAME,
            "${NotesTable.Column.ID.columnName} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null,
        ).use { cursor ->
            cursor.moveToNext()
            return cursor.toNote()
        }
    }
}