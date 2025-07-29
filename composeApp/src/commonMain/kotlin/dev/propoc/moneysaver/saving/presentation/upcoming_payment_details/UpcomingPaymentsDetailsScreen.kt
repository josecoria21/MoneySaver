package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.propoc.moneysaver.core.presentation.moneyFormat
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

@Composable
fun UpcomingPaymentsDetailsScreenRoot(
    upcomingPayments: UpcomingPayment,
    onBackClick: () -> Unit
) {

    UpcomingPaymentsDetailsScreen(
        upcomingPayment = upcomingPayments,
        onAction = { action ->
            when(action) {
                is UpcomingPaymentDetailsAction.OnBackClick -> onBackClick()
                is UpcomingPaymentDetailsAction.OnSelectedUpcomingPaymentChange -> TODO()
                is UpcomingPaymentDetailsAction.OnEdit -> {}
                is UpcomingPaymentDetailsAction.OnSaveClick -> {
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpcomingPaymentsDetailsScreen(
    upcomingPayment: UpcomingPayment,
    onAction: (UpcomingPaymentDetailsAction) -> Unit
) {
    // ─── UI state ────────────────────────────────────────────────────────────────
    var isEditing by remember { mutableStateOf(false) }

    // start from the existing values
    var name by remember { mutableStateOf(TextFieldValue(upcomingPayment.name)) }
    var date by remember { mutableStateOf(TextFieldValue(upcomingPayment.date)) }
    var amount by remember { mutableStateOf(TextFieldValue(upcomingPayment.amount.toString())) }

    // track a simple “blank” error
    val hasError = remember(name, date, amount) {
        name.text.isBlank() || date.text.isBlank() || amount.text.isBlank()
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        if (isEditing) "Edit Payment" else "Payment Details",
                        textAlign = TextAlign.Start
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(UpcomingPaymentDetailsAction.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (isEditing) {
                        IconButton(onClick = {
                            if (!hasError) {
                                // fire save with a new copy
                                val updated = upcomingPayment.copy(
                                    name = name.text,
                                    date = date.text,
                                    amount = amount.text.toDoubleOrNull() ?: upcomingPayment.amount
                                )
                                onAction(UpcomingPaymentDetailsAction.OnSaveClick(updated))
                                isEditing = false
                            }
                        }) {
                            Icon(Icons.Default.Save, contentDescription = "Save")
                        }
                    } else {
                        IconButton(onClick = {
                            onAction(UpcomingPaymentDetailsAction.OnEdit(upcomingPayment))
                            isEditing = true
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    if (isEditing) {
                        // ─── Editable fields ─────────────────────────────────────────────
                        OutlinedTextField(
                            value = name,
                            isError = name.text.isBlank(),
                            label = { Text("Name") },
                            onValueChange = { name = it }
                        )
                        OutlinedTextField(
                            value = date,
                            isError = date.text.isBlank(),
                            label = { Text("Due date") },
                            onValueChange = { date = it }
                        )
                        OutlinedTextField(
                            value = amount,
                            isError = amount.text.isBlank(),
                            label = { Text("Amount") },
                            onValueChange = { amount = it },
                            singleLine = true
                        )
                        if (hasError) {
                            Text(
                                "All fields must be filled",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    } else {
                        // ─── Read‑only display ───────────────────────────────────────────
                        Text(
                            text = upcomingPayment.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Due date: ${upcomingPayment.date}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = upcomingPayment.amount.moneyFormat(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}
