package ru.test.applications.sqlite.dbCourseMaxim

object NotesTable {
    const val NAME = "notes"
    const val VERSION = 1

    val DDL = """
        CREATE TABLE $NAME (
            ${Column.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${Column.TITLE} TEXT NOT NULL,
            ${Column.CONTENT} TEXT NOT NULL
        );
    """.trimIndent()

    val ALL_COLUMN_NAME = Column.values().map {
        it.columnName
    }.toTypedArray()

    enum class Column(val columnName: String) {
        ID("ID"),
        TITLE("TITLE"),
        CONTENT("CONTENT")
    }
}