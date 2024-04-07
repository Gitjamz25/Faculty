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
    fun updateMember(originalName: String, newName: String, newEmail: String, newPhone: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, newName)
            put(COLUMN_EMAIL, newEmail)
            put(COLUMN_PHONE, newPhone)
        }
        db.update(TABLE_NAME, values, "$COLUMN_NAME = ?", arrayOf(originalName))
        db.close()
    }
    fun deleteMember(memberName: String) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_NAME = ?", arrayOf(memberName))
        db.close()
    }
    fun deleteAllData() {
        val db = writableDatabase
        db.delete("members", null, null)
        db.close()
    }
    fun getAllMembers(): List<Member> {
        val members = mutableListOf<Member>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor?.use { cursor ->
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))
                val member = Member(name, email, phone)
                members.add(member)
            }
        }
        cursor?.close()
        db.close()
        return members
    }


}