package ru.test.applications.sqlite.dbCourseMaxim

object NotesTable {
    const val NAME = "notes"
    const val VERSION = 1

    val DDL = """
        CREATE TABLE $NAME (
            ${Column.ID.columnName} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${Column.TITLE.columnName} TEXT NOT NULL,
            ${Column.CONTENT.columnName} TEXT NOT NULL
        );
    """.trimIndent()

    val ALL_COLUMN_NAME = Column.values().map {
        it.columnName
    }.toTypedArray()

    enum class Column(val columnName: String) {
        ID("id"),
        TITLE("title"),
        CONTENT("content")
    }
}