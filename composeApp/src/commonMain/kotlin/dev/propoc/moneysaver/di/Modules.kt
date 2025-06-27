package dev.propoc.moneysaver.di

import dev.propoc.moneysaver.saving.presentation.SelectedUpcomingPaymentViewModel
import dev.propoc.moneysaver.saving.presentation.landing.LandingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    viewModelOf(::LandingViewModel)
    viewModelOf(::SelectedUpcomingPaymentViewModel)
}