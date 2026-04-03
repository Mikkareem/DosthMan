@file:OptIn(ExperimentalObjCRefinement::class)

package com.techullurgy.dosthman.di

import com.techullurgy.dosthman.data.di.DataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.plugin.module.dsl.startKoin
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@HiddenFromObjC
@Module(includes = [DataModule::class])
@ComponentScan("com.techullurgy.dosthman")
internal class AppModule

@HiddenFromObjC
@KoinApplication(
    modules = [AppModule::class]
)
internal class DosthManApp