package dev.propoc.moneysaver.saving.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UpcomingPaymentEntity::class],
    version = 1
)
abstract class UpcomingPaymentDatabase : RoomDatabase() {

    abstract val upcomingPaymentDao: UpcomingPaymentDao

    companion object {
        const val UPCOMING_PAYMENT_DATABASE = "upcoming_payment_db"
    }
}