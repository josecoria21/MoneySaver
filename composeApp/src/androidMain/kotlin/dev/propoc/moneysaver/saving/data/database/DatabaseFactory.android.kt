package dev.propoc.moneysaver.saving.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun createDatabase(): RoomDatabase.Builder<UpcomingPaymentDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(UpcomingPaymentDatabase.UPCOMING_PAYMENT_DATABASE)

        return Room.databaseBuilder<UpcomingPaymentDatabase>(
            context = appContext,
            name = dbFile.absolutePath)
    }
}