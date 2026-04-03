package com.techullurgy.dosthman.data.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.techullurgy.dosthman.data.room.DosthManDatabase
import com.techullurgy.dosthman.data.room.RequestResponseDao
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatform

@Module(includes = [PlatformDataModule::class])
@ComponentScan("com.techullurgy.dosthman.data")
class DataModule {

    @Single
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json()
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    @Single
    fun provideDosthManDatabase(
        builder: RoomDatabase.Builder<DosthManDatabase>
    ): DosthManDatabase {
        return builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    @Single
    fun provideRequestResponseDao(
        database: DosthManDatabase
    ): RequestResponseDao {
        return database.requestResponseDao()
    }

    @Single
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Single
    fun provideKoinScope(): Scope = KoinPlatform.getKoin().getScope("_root_")
}