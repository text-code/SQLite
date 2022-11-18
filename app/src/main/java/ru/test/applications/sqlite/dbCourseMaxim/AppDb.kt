package ru.test.applications.sqlite.dbCourseMaxim

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class AppDb private constructor(db: SQLiteDatabase) {
    val noteDao: NoteDao = NoteDaoImpl(db)

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: AppDb(
                    buildDatabase(context, arrayOf(NotesTable.DDL))
                ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, DDLs: Array<String>) = DbHelper(
            context, NotesTable.VERSION, NotesTable.NAME, DDLs
        ).writableDatabase
    }


}