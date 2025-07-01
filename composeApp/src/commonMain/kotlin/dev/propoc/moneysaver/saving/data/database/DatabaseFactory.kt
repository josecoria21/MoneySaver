package dev.propoc.moneysaver.saving.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory  {
    fun createDatabase(): RoomDatabase.Builder<UpcomingPaymentDatabase>
}