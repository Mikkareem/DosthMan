package com.techullurgy.dosthman.di

import org.koin.dsl.KoinAppDeclaration
import org.koin.plugin.module.dsl.startKoin

fun initApp(declaration: KoinAppDeclaration? = null) = startKoin<DosthManApp>(declaration)