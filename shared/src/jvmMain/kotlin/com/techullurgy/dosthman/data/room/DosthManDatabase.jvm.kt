package com.techullurgy.dosthman.data.room

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<DosthManDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "dosthman_db.db")
    return Room.databaseBuilder<DosthManDatabase>(
        name = dbFile.absolutePath,
    )
}