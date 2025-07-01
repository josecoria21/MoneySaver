package dev.propoc.moneysaver.di

import dev.propoc.moneysaver.saving.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module{
        single {
            DatabaseFactory()
        }
    }