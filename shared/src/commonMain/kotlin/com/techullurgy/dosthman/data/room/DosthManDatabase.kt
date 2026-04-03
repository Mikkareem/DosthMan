package com.techullurgy.dosthman.data.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters

@Database(
    entities = [RequestResponseEntity::class],
    version = 1
)
@TypeConverters(PersistentRequestResponseTypeConverter::class)
@ConstructedBy(DosthManDatabaseConstructor::class)
abstract class DosthManDatabase: RoomDatabase() {
    abstract fun requestResponseDao(): RequestResponseDao
}

@Suppress("KotlinNoActualForExpect")
expect object DosthManDatabaseConstructor : RoomDatabaseConstructor<DosthManDatabase> {
    override fun initialize(): DosthManDatabase
}