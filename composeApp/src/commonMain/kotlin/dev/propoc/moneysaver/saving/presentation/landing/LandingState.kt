package dev.propoc.moneysaver.saving.presentation.landing

import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

data class LandingState (
    val isLoading: Boolean = true,
    val upcomingPayments: List<UpcomingPayment> = emptyList(),
)