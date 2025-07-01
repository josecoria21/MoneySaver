package dev.propoc.moneysaver.di

import dev.propoc.moneysaver.saving.data.database.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module{
        single {
            DatabaseFactory(androidApplication())
        }
    }