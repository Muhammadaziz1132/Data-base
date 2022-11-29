package com.example.data_oken.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.data_oken.db.MyConst.DB_NAME
import com.example.data_oken.db.MyConst.ID
import com.example.data_oken.db.MyConst.NAME
import com.example.data_oken.db.MyConst.NUMBER
import com.example.data_oken.db.MyConst.TABLE_NAME
import com.example.data_oken.models.MyContact

class MyDbHelper(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, 1)
    ,MyDbInterface {

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($ID integer not null primary key autoincrement unique, $NAME, text not null, $NUMBER text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addContact(myContact: MyContact) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, myContact.name)
        contentValues.put(NUMBER, myContact.number)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()

    }

    override fun getAllContact(): List<MyContact> {
        val list = ArrayList<MyContact>()
        val database = readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val myContact = MyContact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(myContact)
            } while (cursor.moveToNext())
        }
        return  list
    }
}