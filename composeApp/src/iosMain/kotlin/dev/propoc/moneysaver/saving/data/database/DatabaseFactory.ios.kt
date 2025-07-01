@file:OptIn(ExperimentalForeignApi::class)

package dev.propoc.moneysaver.saving.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
    actual fun createDatabase(): RoomDatabase.Builder<UpcomingPaymentDatabase> {
        val dbFile = documentDirectory() + "/${UpcomingPaymentDatabase.UPCOMING_PAYMENT_DATABASE}"
        return Room.databaseBuilder<UpcomingPaymentDatabase>(
            name = dbFile
        )
    }

    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path)
    }
}