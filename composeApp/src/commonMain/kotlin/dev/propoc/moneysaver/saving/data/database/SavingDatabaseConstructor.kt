package dev.propoc.moneysaver.saving.data.database

import androidx.room.RoomDatabaseConstructor

// KMP needs this to initialize the database
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object SavingDatabaseConstructor: RoomDatabaseConstructor<UpcomingPaymentDatabase> {
    override fun initialize(): UpcomingPaymentDatabase
}