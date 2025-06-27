package dev.propoc.moneysaver.saving.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UpcomingPayment(
    val id: String,
    val date: String,
    val amount: Int,
    val name: String,
)
