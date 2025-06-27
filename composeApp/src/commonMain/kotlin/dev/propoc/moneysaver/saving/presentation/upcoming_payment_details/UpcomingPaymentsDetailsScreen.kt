package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.propoc.moneysaver.core.presentation.moneyFormat
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

@Composable
fun UpcomingPaymentsDetailsScreenRoot(
    upcomingPayments: UpcomingPayment,
    onBackClick: () -> Unit
) {

    UpcomingPaymentsDetailsScreen(
        upcomingPayments = upcomingPayments,
        onAction = { action ->
            when(action) {
                is UpcomingPaymentDetailsAction.OnBackClick -> onBackClick()
            }
        }
    )
}

@Composable
fun UpcomingPaymentsDetailsScreen(
    upcomingPayments: UpcomingPayment,
    onAction: (UpcomingPaymentDetailsAction) -> Unit
    ) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = upcomingPayments.name)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = upcomingPayments.date)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = upcomingPayments.amount.moneyFormat())
    }
}
