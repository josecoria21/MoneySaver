package dev.propoc.moneysaver.saving.presentation.landing

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.propoc.moneysaver.core.presentation.FabAction
import dev.propoc.moneysaver.core.presentation.MultiActionFab
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
    // hoist the FAB’s expanded state
    var fabExpanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        // ─── your existing content ──────────────────────────────────────────────
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.height(12.dp))
            AccountSummary(Modifier.padding(16.dp))
            Spacer(Modifier.height(12.dp))
            UpcomingPayments(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                upcomingPayments = state.upcomingPayments,
                onUpcomingPaymentClick = {
                    onAction(LandingAction.OnUpcomingPaymentClick(it))
                }
            )
            Spacer(Modifier.height(12.dp))
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

        // ─── transparent overlay when FAB is expanded ─────────────────────────
        if (fabExpanded) {
            Box(
                Modifier
                    .fillMaxSize()
                    // invisible, but consumes clicks
                    .pointerInput(Unit) {
                        detectTapGestures { fabExpanded = false }
                    }
            )
        }

        // ─── your FAB, now controlled from here ───────────────────────────────
        MultiActionFab(
            expanded = fabExpanded,
            onExpandedChange = { fabExpanded = it },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(16.dp),
            actions = listOf(
                FabAction(
                    icon = { Icon(Icons.Default.AccountBalance, contentDescription = "Edit") },
                    label = "Edit item",
                    onClick = { /* TODO */ }
                ),
                FabAction(
                    icon = { Icon(Icons.Default.Share, contentDescription = "Share") },
                    label = "Share item",
                    onClick = { /* TODO */ }
                )
            )
        )
    }
}


fun generatePayment() = UpcomingPayment(
    id = Random.nextLong().toString(),
    date = "2023-01-01",
    amount = 100.00,
    name = "Payment 22"
)
