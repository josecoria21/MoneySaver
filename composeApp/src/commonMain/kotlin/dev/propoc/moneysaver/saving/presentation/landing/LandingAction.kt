package dev.propoc.moneysaver.saving.presentation.landing

import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

sealed interface LandingAction {
    data class OnUpcomingPaymentClick(val upcomingPayment: UpcomingPayment): LandingAction
    data class OnAddUpcomingPaymentClick(val upcomingPayment: UpcomingPayment): LandingAction
}