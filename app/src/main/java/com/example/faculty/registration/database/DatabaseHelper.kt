package com.example.faculty.registration.database


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.faculty.Member
import com.example.faculty.User
// DatabaseHelper.kt
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "members.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "members"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_NAME TEXT PRIMARY KEY," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_PHONE TEXT)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(sql)
        onCreate(db)
    }

    fun addMember(member: Member) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, member.name)
            put(COLUMN_EMAIL, member.email)
            put(COLUMN_PHONE, member.phone)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

}