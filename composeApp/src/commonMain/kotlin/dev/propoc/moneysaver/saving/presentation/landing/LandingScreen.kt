package dev.propoc.moneysaver.saving.presentation.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment
import dev.propoc.moneysaver.saving.presentation.landing.components.AccountSummary
import dev.propoc.moneysaver.saving.presentation.landing.components.UpcomingPayments

@Composable
fun LandingScreenRoot(
    viewModel: LandingViewModel,
    onUpcomingPaymentClick: (UpcomingPayment) -> Unit
) {
    LandingScreen(
        onAction = { action ->
            when(action) {
                is LandingAction.OnUpcomingPaymentClick -> onUpcomingPaymentClick(action.upcomingPayment)
            }
        }
    )
}

@Composable
fun LandingScreen(
    onAction: (LandingAction) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))

        AccountSummary(modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(12.dp))

        UpcomingPayments(
            upcomingPayments = upcomingPayments,
            onUpcomingPaymentClick = {
                onAction(LandingAction.OnUpcomingPaymentClick(it))
            }
        )
    }
}


val upcomingPayments = listOf(
    UpcomingPayment(
        id = "1",
        date = "2023-01-01",
        amount = 100,
        name = "Payment 12"
    ),
    UpcomingPayment(
        id = "2",
        date = "2023-01-01",
        amount = 100,
        name = "Payment 13"
    ),
    UpcomingPayment(
        id = "3",
        date = "2023-01-01",
        amount = 100,
        name = "Payment 14"
    )
)
