package dev.propoc.moneysaver.saving.data.mapper

import dev.propoc.moneysaver.saving.data.database.UpcomingPaymentEntity
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment

fun UpcomingPayment.toUpcomingPayment(): UpcomingPaymentEntity {
    return UpcomingPaymentEntity(
        id = id,
        date = date,
        amount = amount,
        name = name
    )
}