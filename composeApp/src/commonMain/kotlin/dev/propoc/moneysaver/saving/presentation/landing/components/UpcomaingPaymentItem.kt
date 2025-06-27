package dev.propoc.moneysaver.saving.presentation.landing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.propoc.moneysaver.core.presentation.moneyFormat
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

@Composable
fun UpcomingPaymentItem(
    upcomingPayment: UpcomingPayment,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable( onClick = onClick )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = upcomingPayment.date,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = upcomingPayment.name,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = upcomingPayment.amount.moneyFormat(),
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}
