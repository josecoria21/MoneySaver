package dev.propoc.moneysaver.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.propoc.moneysaver.saving.data.database.DatabaseFactory
import dev.propoc.moneysaver.saving.data.database.UpcomingPaymentDatabase
import dev.propoc.moneysaver.saving.presentation.SelectedUpcomingPaymentViewModel
import dev.propoc.moneysaver.saving.presentation.landing.LandingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::LandingViewModel)
    viewModelOf(::SelectedUpcomingPaymentViewModel)

    // ROOM
    single {
        get<DatabaseFactory>().createDatabase()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<UpcomingPaymentDatabase>().upcomingPaymentDao
    }
}