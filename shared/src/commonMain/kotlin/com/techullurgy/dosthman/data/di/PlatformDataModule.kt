package com.techullurgy.dosthman.data.di

import androidx.room.RoomDatabase
import com.techullurgy.dosthman.data.room.DosthManDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Module
expect class PlatformDataModule {
    @Single
    fun provideRoomDatabaseBuilder(scope: Scope): RoomDatabase.Builder<DosthManDatabase>
}