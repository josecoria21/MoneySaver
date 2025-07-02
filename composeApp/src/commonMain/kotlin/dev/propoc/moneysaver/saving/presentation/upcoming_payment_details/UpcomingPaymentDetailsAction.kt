package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

sealed interface UpcomingPaymentDetailsAction {
    data object OnBackClick: UpcomingPaymentDetailsAction
    data object onSaveClick: UpcomingPaymentDetailsAction
}