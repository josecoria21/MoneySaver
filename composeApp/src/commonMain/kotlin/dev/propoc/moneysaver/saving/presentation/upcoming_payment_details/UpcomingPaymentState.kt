package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

data class UpcomingPaymentState(
    val selectedUpcomingPayment: UpcomingPayment? = null
)
