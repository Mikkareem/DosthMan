package com.techullurgy.dosthman.data.di

import androidx.room.RoomDatabase
import com.techullurgy.dosthman.data.room.DosthManDatabase
import com.techullurgy.dosthman.data.room.getDatabaseBuilder
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Module
actual class PlatformDataModule {
    @Single
    actual fun provideRoomDatabaseBuilder(scope: Scope): RoomDatabase.Builder<DosthManDatabase> {
        return getDatabaseBuilder()
    }
}