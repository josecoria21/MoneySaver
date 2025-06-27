package dev.propoc.moneysaver.saving.presentation.landing.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccountSummary(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "$1000",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "$200"
        )
        Row {
            Text(text = "Savings")
            Text(text = "Expenses")
        }
    }
}