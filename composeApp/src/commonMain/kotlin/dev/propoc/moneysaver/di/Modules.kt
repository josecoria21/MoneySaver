package dev.propoc.moneysaver.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.propoc.moneysaver.saving.data.DefaultSavingRepositoryImpl
import dev.propoc.moneysaver.saving.data.database.DatabaseFactory
import dev.propoc.moneysaver.saving.data.database.UpcomingPaymentDatabase
import dev.propoc.moneysaver.saving.domain.repository.DefaultSavingRepository
import dev.propoc.moneysaver.saving.presentation.SelectedUpcomingPaymentViewModel
import dev.propoc.moneysaver.saving.presentation.landing.LandingViewModel
import dev.propoc.moneysaver.saving.presentation.upcoming_payment_details.UpcomingPaymentsDetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::LandingViewModel)
    viewModelOf(::SelectedUpcomingPaymentViewModel)
    viewModelOf(::UpcomingPaymentsDetailsViewModel)

    singleOf(::DefaultSavingRepositoryImpl).bind<DefaultSavingRepository>()

    // ROOM
    single {
        get<DatabaseFactory>().createDatabase()
            .setDriver(BundledSQLiteDriver())
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single {
        get<UpcomingPaymentDatabase>().upcomingPaymentDao
    }
}