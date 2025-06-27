package dev.propoc.moneysaver.saving.presentation.landing.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

@Composable
fun UpcomingPayments(
    upcomingPayments: List<UpcomingPayment>,
    onUpcomingPaymentClick: (UpcomingPayment) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
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
