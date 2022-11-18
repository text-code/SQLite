package ru.test.applications.sqlite.dbYouTube

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = dbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String) {
        val values = ContentValues().apply {
            put(DbName.COLUMN_NAME_TITLE, title)
            put(DbName.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(DbName.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbData(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(
            DbName.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
        )

            while (cursor?.moveToNext()!!) {
                val dataText = cursor.getString(cursor.getColumnIndex(DbName.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        dbHelper.close()
    }
}