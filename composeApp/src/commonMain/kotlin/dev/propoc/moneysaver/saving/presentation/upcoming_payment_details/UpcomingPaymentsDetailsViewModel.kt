package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import androidx.lifecycle.ViewModel
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

class UpcomingPaymentsDetailsViewModel: ViewModel() {

    fun getUpcomingPayments(): UpcomingPayment {
        return UpcomingPayment(
            id = "1",
            date = "2023-01-01",
            amount = 100,
            name = "Payment 1"
        )
    }
}