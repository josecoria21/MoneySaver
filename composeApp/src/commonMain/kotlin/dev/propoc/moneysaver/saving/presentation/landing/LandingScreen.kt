package dev.propoc.moneysaver.saving.presentation.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment
import dev.propoc.moneysaver.saving.presentation.landing.components.AccountSummary
import dev.propoc.moneysaver.saving.presentation.landing.components.UpcomingPayments
import kotlin.random.Random

@Composable
fun LandingScreenRoot(
    viewModel: LandingViewModel,
    onUpcomingPaymentClick: (UpcomingPayment) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LandingScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is LandingAction.OnUpcomingPaymentClick -> onUpcomingPaymentClick(action.upcomingPayment)
                is LandingAction.OnAddUpcomingPaymentClick -> viewModel.onAction(action)
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun LandingScreen(
    state: LandingState,
    onAction: (LandingAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(12.dp))

        AccountSummary(modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(12.dp))

        UpcomingPayments(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            upcomingPayments = state.upcomingPayments,
            onUpcomingPaymentClick = {
                onAction(LandingAction.OnUpcomingPaymentClick(it))
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .navigationBarsPadding(),
            onClick = {
                onAction(LandingAction.OnAddUpcomingPaymentClick(generatePayment()))
            }
        ) {
            Text("Add Upcoming Payment")
        }
    }
}


fun generatePayment() = UpcomingPayment(
    id = Random.nextLong().toString(),
    date = "2023-01-01",
    amount = 100,
    name = "Payment 22"
)

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
