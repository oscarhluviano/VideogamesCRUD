package com.example.videogamescrud.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_GAMES (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, genre TEXT NOT NULL, developer TEXT NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_GAMES")
        onCreate(p0)
    }

    //private static final int SALUDO = 1  -> Así se manejan los statics en java

    //Así se manejan los statics en Kotlin
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "videogames.db"
        public const val TABLE_GAMES = "games"
    }
}