package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

sealed interface UpcomingPaymentDetailsAction {
    data object OnBackClick: UpcomingPaymentDetailsAction
    data object onSaveClick: UpcomingPaymentDetailsAction
    data class OnSelectedUpcomingPaymentChange(val upcomingPayment: UpcomingPayment): UpcomingPaymentDetailsAction

}