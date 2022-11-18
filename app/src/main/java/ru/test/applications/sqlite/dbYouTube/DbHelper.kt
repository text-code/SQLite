package ru.test.applications.sqlite.dbYouTube

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(
    context: Context
) : SQLiteOpenHelper(
    context, DbName.DATABASE_NAME,
    null,
    DbName.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbName.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DbName.SQL_DELETE_TABLE)
        onCreate(db)
    }
}