package dev.propoc.moneysaver.saving.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UpcomingPaymentEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val date: String,
    val amount: Int,
    val name: String,
)