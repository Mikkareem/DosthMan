package com.techullurgy.dosthman.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<DosthManDatabase> {
    val appContext: Context = context.applicationContext
    val dbFile = appContext.getDatabasePath("dosthman_db.db")
    return Room.databaseBuilder<DosthManDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}