package dev.propoc.moneysaver.saving.presentation.landing.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

@Composable
fun UpcomingPayments(
    modifier: Modifier = Modifier,
    upcomingPayments: List<UpcomingPayment>,
    onUpcomingPaymentClick: (UpcomingPayment) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = upcomingPayments,
            key = { it.id }
        ) { payment ->
            UpcomingPaymentItem(
                upcomingPayment = payment,
                onClick = {
                    onUpcomingPaymentClick(payment)
                }
            )
        }
    }
}
