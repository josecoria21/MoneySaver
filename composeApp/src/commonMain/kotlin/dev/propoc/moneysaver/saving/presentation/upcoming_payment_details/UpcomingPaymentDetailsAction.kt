package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

sealed interface UpcomingPaymentDetailsAction {
    data object OnBackClick: UpcomingPaymentDetailsAction
    data class OnSelectedUpcomingPaymentChange(val upcomingPayment: UpcomingPayment): UpcomingPaymentDetailsAction
    data class OnEdit(val upcomingPayment: UpcomingPayment): UpcomingPaymentDetailsAction
    data class OnSaveClick(val upcomingPayment: UpcomingPayment): UpcomingPaymentDetailsAction
}